package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Crontab;
import com.javaweb.system.mapper.CrontabMapper;
import com.javaweb.system.query.CrontabQuery;
import com.javaweb.system.service.ICrontabService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.crontab.CrontabListVo;
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
 * 定时任务表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class CrontabServiceImpl extends BaseServiceImpl<CrontabMapper, Crontab> implements ICrontabService {

    @Autowired
    private CrontabMapper crontabMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        CrontabQuery crontabQuery = (CrontabQuery) query;
        // 查询条件
        QueryWrapper<Crontab> queryWrapper = new QueryWrapper<>();
        // 任务标题
        if (!StringUtils.isEmpty(crontabQuery.getTitle())) {
            queryWrapper.like("title", crontabQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<Crontab> page = new Page<>(crontabQuery.getPageIndex(), crontabQuery.getPageSize());
        IPage<Crontab> data = crontabMapper.selectPage(page, queryWrapper);
        List<Crontab> crontabList = data.getRecords();
        List<CrontabListVo> crontabListVoList = new ArrayList<>();
        if (!crontabList.isEmpty()) {
            crontabList.forEach(item -> {
                CrontabListVo crontabListVo = new CrontabListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, crontabListVo);
                crontabListVoList.add(crontabListVo);
            });
        }
        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", crontabListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 添加、编辑定时任务
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Crontab entity) {
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
     * 删除定时任务
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(Crontab entity) {
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
    public JsonResult setStatus(Crontab entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }

}
