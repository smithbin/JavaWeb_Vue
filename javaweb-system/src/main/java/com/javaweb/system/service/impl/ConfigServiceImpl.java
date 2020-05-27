package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.constant.ConfigConstant;
import com.javaweb.system.entity.Config;
import com.javaweb.system.entity.ConfigGroup;
import com.javaweb.system.mapper.ConfigGroupMapper;
import com.javaweb.system.mapper.ConfigMapper;
import com.javaweb.system.query.ConfigQuery;
import com.javaweb.system.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.config.ConfigListVo;
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
 * 配置表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class ConfigServiceImpl extends BaseServiceImpl<ConfigMapper, Config> implements IConfigService {

    @Autowired
    private ConfigMapper configMapper;

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
        ConfigQuery configQuery = (ConfigQuery) query;
        // 查询条件
        QueryWrapper<Config> queryWrapper = new QueryWrapper<>();
        // 配置标题
        if (!StringUtils.isEmpty(configQuery.getTitle())) {
            queryWrapper.like("title", configQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        IPage<Config> page = new Page<>(configQuery.getPageIndex(), configQuery.getPageSize());
        IPage<Config> data = configMapper.selectPage(page, queryWrapper);
        List<Config> configList = data.getRecords();
        List<ConfigListVo> configListVoList = new ArrayList<>();
        if (!configList.isEmpty()) {
            configList.forEach(item -> {
                ConfigListVo configListVo = new ConfigListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, configListVo);
                //配置类型
                configListVo.setTypeName(ConfigConstant.CONFIG_TYPE_LIST.get(configListVo.getType()));
                // 获取配置分组
                ConfigGroup configGroupInfo = configGroupMapper.selectById(configListVo.getGroupId());
                configListVo.setGroupName(configGroupInfo.getName());
                configListVoList.add(configListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", configListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 添加、编辑配置
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Config entity) {
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
     * 删除配置
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(Config entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不存在");
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
    public JsonResult setStatus(Config entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }

    /**
     * 获取基础参数
     *
     * @return
     */
    @Override
    public JsonResult getParamList() {
        Map<String, Object> result = new HashMap<>();
        result.put("type_list", ConfigConstant.CONFIG_TYPE_LIST);
        return JsonResult.success("操作成功", result);
    }

}
