package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.entity.OperLog;
import com.javaweb.system.mapper.OperLogMapper;
import com.javaweb.system.query.OperLogQuery;
import com.javaweb.system.service.IOperLogService;
import com.javaweb.system.vo.operlog.OperLogListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class OperLogServiceImpl extends BaseServiceImpl<OperLogMapper, OperLog> implements IOperLogService {

    @Autowired
    private OperLogMapper operLogMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        OperLogQuery operLogQuery = (OperLogQuery) query;
        // 查询条件
        QueryWrapper<OperLog> queryWrapper = new QueryWrapper<>();
        // 日志标题
        if (!StringUtils.isEmpty(operLogQuery.getTitle())) {
            queryWrapper.like("title", operLogQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<OperLog> page = new Page<>(operLogQuery.getPageIndex(), operLogQuery.getPageSize());
        IPage<OperLog> data = operLogMapper.selectPage(page, queryWrapper);
        List<OperLog> operLogList = data.getRecords();
        List<OperLogListVo> operLogListVoList = new ArrayList<>();
        if (!operLogList.isEmpty()) {
            operLogList.forEach(item -> {
                OperLogListVo operLogListVo = new OperLogListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, operLogListVo);
                operLogListVoList.add(operLogListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", operLogListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 删除日志
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(OperLog entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不能为空");
        }
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 创建系统操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(OperLog operLog) {
        operLogMapper.insertOperlog(operLog);
    }
}
