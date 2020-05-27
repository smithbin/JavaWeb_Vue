package com.javaweb.system.controller;


import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.OperLog;
import com.javaweb.system.query.OperLogQuery;
import com.javaweb.system.service.IOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/operlog")
public class OperLogController extends BaseController {

    @Autowired
    private IOperLogService operLogService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody OperLogQuery query) {
        return operLogService.getList(query);
    }

    /**
     * 删除日志
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody OperLog entity) {
        return operLogService.delete(entity);
    }

}
