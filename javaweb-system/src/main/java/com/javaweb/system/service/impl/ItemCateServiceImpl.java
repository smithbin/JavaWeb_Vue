package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.ItemCate;
import com.javaweb.system.mapper.ItemCateMapper;
import com.javaweb.system.query.ItemCateQuery;
import com.javaweb.system.service.IItemCateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.service.IItemService;
import com.javaweb.system.vo.item.ItemInfoVo;
import com.javaweb.system.vo.itemcate.ItemCateInfoVo;
import com.javaweb.system.vo.itemcate.ItemCateListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 栏目管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class ItemCateServiceImpl extends BaseServiceImpl<ItemCateMapper, ItemCate> implements IItemCateService {

    @Autowired
    private ItemCateMapper itemCateMapper;

    @Autowired
    private IItemService itemService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ItemCateQuery itemCateQuery = (ItemCateQuery) query;
        // 查询条件
        QueryWrapper<ItemCate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", 0);
        // 栏目名称
        if (!StringUtils.isEmpty(itemCateQuery.getName())) {
            queryWrapper.like("name", itemCateQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        IPage<ItemCate> page = new Page<>(itemCateQuery.getPageIndex(), itemCateQuery.getPageSize());
        IPage<ItemCate> data = itemCateMapper.selectPage(page, queryWrapper);
        List<ItemCate> itemCateList = data.getRecords();
        List<ItemCateListVo> itemCateListVoList = new ArrayList<>();
        if (!itemCateList.isEmpty()) {
            itemCateList.forEach(item -> {
                ItemCateListVo itemCateListVo = new ItemCateListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, itemCateListVo);
                // 封面处理
                if (!StringUtils.isEmpty(itemCateListVo.getCover())) {
                    itemCateListVo.setCover(CommonUtils.getImageURL(itemCateListVo.getCover()));
                }
                // 获取站点
                if (itemCateListVo.getItemId() > 0) {
                    ItemInfoVo itemInfo = (ItemInfoVo) itemService.getInfo(itemCateListVo.getItemId());
                    itemCateListVo.setItemName(itemInfo.getName());
                }
                // 获取子级数据
                itemCateListVo.setChildren(this.getChildCateList(item.getId()));
                itemCateListVoList.add(itemCateListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", itemCateListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 获取子级栏目
     *
     * @param pid 父级ID
     * @return
     */
    public List<ItemCateListVo> getChildCateList(Integer pid) {
        QueryWrapper<ItemCate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        List<ItemCate> ItemCateList = itemCateMapper.selectList(queryWrapper);
        List<ItemCateListVo> itemCateListVoList = new ArrayList<>();
        if (!ItemCateList.isEmpty()) {
            ItemCateList.forEach(item -> {
                ItemCateListVo itemCateListVo = new ItemCateListVo();
                // 复制属性
                BeanUtils.copyProperties(item, itemCateListVo);
                // 封面处理
                if (!StringUtils.isEmpty(itemCateListVo.getCover())) {
                    itemCateListVo.setCover(CommonUtils.getImageURL(itemCateListVo.getCover()));
                }
                // 获取站点
                if (itemCateListVo.getItemId() > 0) {
                    ItemInfoVo itemInfo = (ItemInfoVo) itemService.getInfo(itemCateListVo.getItemId());
                    itemCateListVo.setItemName(itemInfo.getName());
                }
                // 获取子级
                List<ItemCateListVo> childrenItemCateList = this.getChildCateList(item.getId());
                if (childrenItemCateList != null) {
                    itemCateListVo.setChildren(childrenItemCateList);
                }
                itemCateListVoList.add(itemCateListVo);
            });
        }
        return itemCateListVoList;
    }

    /**
     * 获取栏目列表
     *
     * @return
     */
    @Override
    public JsonResult getCateList() {
        List<ItemCateListVo> itemCateListVoList = this.getChildCateList(0);
        return JsonResult.success("操作成功", itemCateListVoList);
    }

    /**
     * 获取详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        ItemCate entity = (ItemCate) super.getInfo(id);
        if (entity != null && !StringUtils.isEmpty(entity.getCover())) {
            entity.setCover(CommonUtils.getImageURL(entity.getCover()));
        }
        // 拷贝属性
        ItemCateInfoVo itemCateInfoVo = new ItemCateInfoVo();
        BeanUtils.copyProperties(entity, itemCateInfoVo);
        return itemCateInfoVo;
    }

    /**
     * 添加、更新栏目
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(ItemCate entity) {
        // 封面处理
        if (!StringUtils.isEmpty(entity.getCover()) && entity.getCover().contains(CommonConfig.imageURL)) {
            entity.setCover(entity.getCover().replaceAll(CommonConfig.imageURL, ""));
        }
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateUser(1);
            entity.setUpdateTime(DateUtils.now());
        } else {
            entity.setCreateUser(1);
            entity.setCreateTime(DateUtils.now());
        }
        return super.edit(entity);
    }

    /**
     * 删除栏目
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(ItemCate entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不能为空");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult setStatus(ItemCate entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("栏目ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("栏目状态不能为空");
        }
        return super.setStatus(entity);
    }

    /**
     * 获取栏目
     *
     * @param cateId 栏目ID
     * @return
     */
    @Override
    public String getCateName(Integer cateId) {
        List<String> nameList = new ArrayList<>();
        while (cateId > 0) {
            ItemCate cateInfo = itemCateMapper.selectById(cateId);
            if (cateInfo != null) {
                nameList.add(cateInfo.getName());
                cateId = cateInfo.getPid();
            } else {
                cateId = 0;
            }
        }
        // 使用集合工具实现数组翻转
        Collections.reverse(nameList);
        return org.apache.commons.lang3.StringUtils.join(nameList.toArray(), ">>");
    }

}
