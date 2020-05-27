package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.ConfigGroup;
import com.javaweb.system.mapper.ConfigGroupMapper;
import com.javaweb.system.query.ConfigGroupQuery;
import com.javaweb.system.service.IConfigGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.configgroup.ConfigGroupListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 配置分组表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class ConfigGroupServiceImpl extends BaseServiceImpl<ConfigGroupMapper, ConfigGroup> implements IConfigGroupService {

    @Autowired
    private ConfigGroupMapper configGroupMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ConfigGroupQuery configGroupQuery = (ConfigGroupQuery) query;
        // 查询条件
        QueryWrapper<ConfigGroup> queryWrapper = new QueryWrapper<>();
        // 配置分组名称
        if (!StringUtils.isEmpty(configGroupQuery.getName())) {
            queryWrapper.like("name", configGroupQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        IPage<ConfigGroup> page = new Page<>(configGroupQuery.getPageIndex(), configGroupQuery.getPageSize());
        IPage<ConfigGroup> data = configGroupMapper.selectPage(page, queryWrapper);
        List<ConfigGroup> configGroupList = data.getRecords();
        List<ConfigGroupListVo> configGroupListVoList = new ArrayList<>();
        if (!configGroupList.isEmpty()) {
            configGroupList.forEach(item -> {
                ConfigGroupListVo configGroupListVo = new ConfigGroupListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, configGroupListVo);
                configGroupListVoList.add(configGroupListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", configGroupListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 添加、编辑配置分组
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(ConfigGroup entity) {
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
     * 删除配置分组
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(ConfigGroup entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不存在");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 获取配置分组列表
     *
     * @return
     */
    @Override
    public JsonResult getGroupList() {
        QueryWrapper<ConfigGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        List<ConfigGroup> configGroupList = configGroupMapper.selectList(queryWrapper);
        return JsonResult.success("操作成功", configGroupList);
    }

}
