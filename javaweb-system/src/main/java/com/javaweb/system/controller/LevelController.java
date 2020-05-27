package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Level;
import com.javaweb.system.query.LevelQuery;
import com.javaweb.system.service.ILevelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 职级表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/level")
public class LevelController extends BaseController {

    @Autowired
    private ILevelService levelService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
//    @RequiresPermissions("level:list")
    public JsonResult list(@RequestBody LevelQuery query) {
        return levelService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "职级管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
//    @RequiresPermissions("level:add")
    public JsonResult add(@RequestBody Level entity) {
        return levelService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 记录ID
     * @return
     */
    @GetMapping("/info")
//    @RequiresPermissions("level:info")
    public JsonResult info(Integer id) {
        return levelService.info(id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "职级管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
//    @RequiresPermissions("level:edit")
    public JsonResult edit(@RequestBody Level entity) {
        return levelService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param entity
     * @return
     */
    @Log(title = "职级管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
//    @RequiresPermissions("level:delete")
    public JsonResult delete(@RequestBody Level entity) {
        return levelService.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody Level entity) {
        return levelService.setStatus(entity);
    }

    /**
     * 获取职级列表
     *
     * @return
     */
    @PostMapping("/getLevelList")
    public JsonResult getLevelList() {
        return levelService.getLevelList();
    }

}
