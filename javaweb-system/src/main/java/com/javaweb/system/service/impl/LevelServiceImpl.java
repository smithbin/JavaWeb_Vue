package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Level;
import com.javaweb.system.mapper.LevelMapper;
import com.javaweb.system.query.LevelQuery;
import com.javaweb.system.service.ILevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.level.LevelInfoVo;
import com.javaweb.system.vo.level.LevelListVo;
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
 * 职级表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class LevelServiceImpl extends BaseServiceImpl<LevelMapper, Level> implements ILevelService {

    @Autowired
    private LevelMapper levelMapper;

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
        LevelQuery levelQuery = (LevelQuery) query;
        // 查询条件
        QueryWrapper<Level> queryWrapper = new QueryWrapper<>();
        // 职级名称
        if (!StringUtils.isEmpty(levelQuery.getName())) {
            queryWrapper.like("name", levelQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Level> page = new Page<>(levelQuery.getPageIndex(), levelQuery.getPageSize());
        IPage<Level> data = levelMapper.selectPage(page, queryWrapper);
        List<Level> levelList = data.getRecords();
        List<LevelListVo> levelListVoList = new ArrayList<>();
        if (!levelList.isEmpty()) {
            levelList.forEach(item -> {
                LevelListVo levelListVo = new LevelListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, levelListVo);
                levelListVoList.add(levelListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", levelListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Level entity = (Level) super.getInfo(id);
        // 返回视图Vo
        LevelInfoVo levelInfoVo = new LevelInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, levelInfoVo);
        return levelInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Level entity) {
        // 字段校验
        Set<ConstraintViolation<Level>> violationSet = validator.validate(entity);
        for (ConstraintViolation<Level> item : violationSet) {
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
    public JsonResult delete(Level entity) {
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
    public JsonResult setStatus(Level entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("职级ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("职级状态不能为空");
        }
        return super.setStatus(entity);
    }

    /**
     * 获取职级列表
     *
     * @return
     */
    @Override
    public JsonResult getLevelList() {
        // 查询条件
        QueryWrapper<Level> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("mark", 1);

        // 查询数据
        List<Level> levelList = levelMapper.selectList(queryWrapper);
        return JsonResult.success("操作成功", levelList);
    }

}
