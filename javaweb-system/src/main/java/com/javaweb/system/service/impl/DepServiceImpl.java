package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Dep;
import com.javaweb.system.mapper.DepMapper;
import com.javaweb.system.query.DepQuery;
import com.javaweb.system.service.IDepService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.dep.DepListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class DepServiceImpl extends BaseServiceImpl<DepMapper, Dep> implements IDepService {

    @Autowired
    private DepMapper depMapper;

    @Autowired
    private Validator validator;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        DepQuery depQuery = (DepQuery) query;
        // 查询条件
        QueryWrapper<Dep> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", 0);
        // 部门名称
        if (!StringUtils.isEmpty(depQuery.getName())) {
            queryWrapper.like("name", depQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        List<Dep> depList = depMapper.selectList(queryWrapper);
        List<DepListVo> depListVoList = new ArrayList<>();
        if (!depList.isEmpty()) {
            depList.forEach(item -> {
                DepListVo depListVo = new DepListVo();
                // 复制属性
                BeanUtils.copyProperties(item, depListVo);
                // 获取子级部门
                depListVo.setChildren(this.getChildDepList(item.getId()));
                depListVoList.add(depListVo);
            });
        }
        return JsonResult.success("操作成功", depListVoList);
    }

    /**
     * 获取部门列表
     *
     * @return
     */
    @Override
    public JsonResult getDeptList() {
        List<DepListVo> depListVoList = this.getChildDepList(0);
        return JsonResult.success("操作成功", depListVoList);
    }

    /**
     * 获取子级部门
     *
     * @param pid 父级ID
     * @return
     */
    public List<DepListVo> getChildDepList(Integer pid) {
        QueryWrapper<Dep> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        List<Dep> depList = depMapper.selectList(queryWrapper);
        List<DepListVo> depListVoList = new ArrayList<>();
        if (!depList.isEmpty()) {
            depList.forEach(item -> {
                DepListVo depListVo = new DepListVo();
                // 复制属性
                BeanUtils.copyProperties(item, depListVo);
                // 获取子级
                List<DepListVo> childrenDepList = this.getChildDepList(item.getId());
                if (childrenDepList != null) {
                    depListVo.setChildren(childrenDepList);
                }
                depListVoList.add(depListVo);
            });
        }
        return depListVoList;
    }

    /**
     * 添加、编辑部门
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Dep entity) {
        // 字段校验
        Set<ConstraintViolation<Dep>> violationSet = validator.validate(entity);
        for (ConstraintViolation<Dep> item : violationSet) {
            return JsonResult.error(item.getMessage());
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
    public JsonResult delete(Dep entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不能为空");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 获取部门名称
     *
     * @param deptId 部门ID
     * @return
     */
    @Override
    public String getDeptName(Integer deptId) {
        List<String> nameList = new ArrayList<>();
        while (deptId > 0) {
            Dep depInfo = depMapper.selectById(deptId);
            if (depInfo != null) {
                nameList.add(depInfo.getName());
                deptId = depInfo.getPid();
            } else {
                deptId = 0;
            }
        }
        // 使用集合工具实现数组翻转
        Collections.reverse(nameList);
        return org.apache.commons.lang3.StringUtils.join(nameList.toArray(), ">>");
    }

}
