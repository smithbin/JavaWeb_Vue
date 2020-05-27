package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Crontab;
import com.javaweb.system.query.CrontabQuery;
import com.javaweb.system.service.ICrontabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 定时任务表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/crontab")
public class CrontabController extends BaseController {

    @Autowired
    private ICrontabService crontabService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody CrontabQuery query) {
        return crontabService.getList(query);
    }

    /**
     * 添加定时任务
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "定时任务管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody Crontab entity) {
        return crontabService.edit(entity);
    }

    /**
     * 编辑定时任务
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "定时任务管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Crontab entity) {
        return crontabService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 定时任务ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return crontabService.info(id);
    }

    /**
     * 删除定时任务
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "定时任务管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Crontab entity) {
        return crontabService.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody Crontab entity) {
        return crontabService.setStatus(entity);
    }

}
