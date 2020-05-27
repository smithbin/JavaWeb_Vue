package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.DicType;
import com.javaweb.system.mapper.DicTypeMapper;
import com.javaweb.system.query.DicTypeQuery;
import com.javaweb.system.service.IDicTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.dictype.DicTypeListVo;
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
 * 字典类型表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class DicTypeServiceImpl extends BaseServiceImpl<DicTypeMapper, DicType> implements IDicTypeService {

    @Autowired
    private DicTypeMapper dicTypeMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        DicTypeQuery dicTypeQuery = (DicTypeQuery) query;
        // 查询条件
        QueryWrapper<DicType> queryWrapper = new QueryWrapper<>();
        // 字典类型名称
        if (!StringUtils.isEmpty(dicTypeQuery.getName())) {
            queryWrapper.like("name", dicTypeQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        IPage<DicType> page = new Page<>(dicTypeQuery.getPageIndex(), dicTypeQuery.getPageSize());
        IPage<DicType> data = dicTypeMapper.selectPage(page, queryWrapper);
        List<DicType> dicTypeList = data.getRecords();
        List<DicTypeListVo> dicTypeListVoList = new ArrayList<>();
        if (!dicTypeList.isEmpty()) {
            dicTypeList.forEach(item -> {
                DicTypeListVo dicTypeListVo = new DicTypeListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, dicTypeListVo);
                dicTypeListVoList.add(dicTypeListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", dicTypeListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 添加、编辑字典类型
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(DicType entity) {
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
     * 删除字典类型
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(DicType entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不存在");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 获取字典类型
     *
     * @return
     */
    @Override
    public JsonResult getDicTypeList() {
        // 查询条件
        QueryWrapper<DicType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        List<DicType> dicTypeList = dicTypeMapper.selectList(queryWrapper);
        return JsonResult.success("操作成功", dicTypeList);
    }

}
