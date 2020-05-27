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
import com.javaweb.system.constant.ConfigConstant;
import com.javaweb.system.entity.Item;
import com.javaweb.system.mapper.ItemMapper;
import com.javaweb.system.query.ItemQuery;
import com.javaweb.system.service.IItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.item.ItemInfoVo;
import com.javaweb.system.vo.item.ItemListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 站点配置表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class ItemServiceImpl extends BaseServiceImpl<ItemMapper, Item> implements IItemService {

    @Autowired
    private ItemMapper itemMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ItemQuery itemQuery = (ItemQuery) query;
        // 查询条件
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        // 站点名称
        if (!StringUtils.isEmpty(itemQuery.getName())) {
            queryWrapper.like("name", itemQuery.getName());
        }
        queryWrapper.eq("mark", 1);

        // 查询数据
        IPage<Item> page = new Page<>(itemQuery.getPageIndex(), itemQuery.getPageSize());
        IPage<Item> data = itemMapper.selectPage(page, queryWrapper);
        List<Item> itemList = data.getRecords();
        List<ItemListVo> itemListVoList = new ArrayList<>();
        if (!itemList.isEmpty()) {
            itemList.forEach(item -> {
                ItemListVo itemListVo = new ItemListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, itemListVo);
                // 站点类型
                if (itemListVo.getType() > 0) {
                    itemListVo.setTypeName(ConfigConstant.ITEM_TYPE_LIST.get(itemListVo.getType()));
                }
                // 图片拼接
                if (!StringUtils.isEmpty(itemListVo.getImage())) {
                    itemListVo.setImage(CommonUtils.getImageURL(itemListVo.getImage()));
                }
                itemListVoList.add(itemListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", itemListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 获取详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Item entity = (Item) super.getInfo(id);
        // 图片
        if (!StringUtils.isEmpty(entity.getImage())) {
            entity.setImage(CommonUtils.getImageURL(entity.getImage()));
        }
        ItemInfoVo itemInfoVo = new ItemInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, itemInfoVo);
        return itemInfoVo;
    }

    /**
     * 添加、更新站点
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Item entity) {
        // 图片处理
        if (!StringUtils.isEmpty(entity.getImage()) && entity.getImage().contains(CommonConfig.imageURL)) {
            entity.setImage(entity.getImage().replaceAll(CommonConfig.imageURL, ""));
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
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(Item entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录不存在");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 获取站点类型列表
     *
     * @return
     */
    @Override
    public JsonResult getItemTypeList() {
        return JsonResult.success("操作成功", ConfigConstant.ITEM_TYPE_LIST);
    }

    /**
     * 获取站点列表
     *
     * @return
     */
    @Override
    public JsonResult getItemList() {
        // 查询条件
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        List<Item> itemList = itemMapper.selectList(queryWrapper);
        return JsonResult.success("操作成功", itemList);
    }

}
