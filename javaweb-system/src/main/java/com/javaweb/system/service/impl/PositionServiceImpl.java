package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Position;
import com.javaweb.system.mapper.PositionMapper;
import com.javaweb.system.query.PositionQuery;
import com.javaweb.system.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.position.PositionInfoVo;
import com.javaweb.system.vo.position.PositionListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 岗位表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class PositionServiceImpl extends BaseServiceImpl<PositionMapper, Position> implements IPositionService {

    @Autowired
    private PositionMapper positionMapper;

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
        PositionQuery positionQuery = (PositionQuery) query;
        // 查询条件
        QueryWrapper<Position> queryWrapper = new QueryWrapper<>();
        // 岗位名称
        if (!StringUtils.isEmpty(positionQuery.getName())) {
            queryWrapper.like("name", positionQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Position> page = new Page<>(positionQuery.getPageIndex(), positionQuery.getPageSize());
        IPage<Position> data = positionMapper.selectPage(page, queryWrapper);
        List<Position> positionList = data.getRecords();
        List<PositionListVo> positionListVoList = new ArrayList<>();
        if (!positionList.isEmpty()) {
            positionList.forEach(item -> {
                PositionListVo positionListVo = new PositionListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, positionListVo);
                positionListVoList.add(positionListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", positionListVoList);
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
        Position entity = (Position) super.getInfo(id);
        // 详情Vo
        PositionInfoVo positionInfoVo = new PositionInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, positionInfoVo);
        return positionInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Position entity) {
        // 字段校验
        Set<ConstraintViolation<Position>> violationSet = validator.validate(entity);
        for (ConstraintViolation<Position> item : violationSet) {
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
    public JsonResult delete(Position entity) {
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
    public JsonResult setStatus(Position entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("岗位ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("岗位状态不能为空");
        }
        return super.setStatus(entity);
    }

    /**
     * 获取岗位列表
     *
     * @return
     */
    @Override
    public JsonResult getPositionList() {
        // 查询条件
        QueryWrapper<Position> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("mark", 1);

        // 查询数据
        List<Position> positionList = positionMapper.selectList(queryWrapper);
        return JsonResult.success("操作成功", positionList);
    }

}
