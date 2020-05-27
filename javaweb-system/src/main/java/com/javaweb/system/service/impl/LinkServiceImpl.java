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
import com.javaweb.system.entity.Link;
import com.javaweb.system.mapper.LinkMapper;
import com.javaweb.system.query.LinkQuery;
import com.javaweb.system.service.IItemCateService;
import com.javaweb.system.service.ILinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.link.LinkInfoVo;
import com.javaweb.system.vo.link.LinkListVo;
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
 * 友链管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class LinkServiceImpl extends BaseServiceImpl<LinkMapper, Link> implements ILinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private IItemCateService itemCateService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        LinkQuery linkQuery = (LinkQuery) query;
        // 查询条件
        QueryWrapper<Link> queryWrapper = new QueryWrapper<>();
        // 名称
        if (!StringUtils.isEmpty(linkQuery.getName())) {
            queryWrapper.like("name", linkQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        IPage<Link> page = new Page<>(linkQuery.getPageIndex(), linkQuery.getPageSize());
        IPage<Link> data = linkMapper.selectPage(page, queryWrapper);
        List<Link> linkList = data.getRecords();
        List<LinkListVo> linkListVoList = new ArrayList<>();
        if (!linkList.isEmpty()) {
            linkList.forEach(item -> {
                LinkListVo linkListVo = new LinkListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, linkListVo);
                // 图片
                if (!StringUtils.isEmpty(linkListVo.getImage())) {
                    linkListVo.setImage(CommonUtils.getImageURL(linkListVo.getImage()));
                }
                // 友链类型
                if (linkListVo.getType() > 0) {
                    linkListVo.setTypeName(ConfigConstant.LINK_TYPE_LIST.get(linkListVo.getType()));
                }
                // 栏目名称
                if (linkListVo.getCateId() > 0) {
                    String cateName = itemCateService.getCateName(linkListVo.getCateId());
                    linkListVo.setCateName(cateName);
                }
                linkListVoList.add(linkListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", linkListVoList);
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
        Link entity = (Link) super.getInfo(id);
        if (!StringUtils.isEmpty(entity.getImage())) {
            entity.setImage(CommonUtils.getImageURL(entity.getImage()));
        }
        // 拷贝属性
        LinkInfoVo linkInfoVo = new LinkInfoVo();
        BeanUtils.copyProperties(entity, linkInfoVo);
        return linkInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Link entity) {
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
    public JsonResult delete(Link entity) {
        if (entity == null || entity.getId() == 0) {
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
    public JsonResult setStatus(Link entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }

    /**
     * 获取参数列表
     *
     * @return
     */
    @Override
    public JsonResult getParamList() {
        Map<String, Object> result = new HashMap<>();
        result.put("type_list", ConfigConstant.LINK_TYPE_LIST);
        result.put("platform_list", ConfigConstant.LINK_PLATFORM_LIST);
        return JsonResult.success("操作成功", result);
    }

}
