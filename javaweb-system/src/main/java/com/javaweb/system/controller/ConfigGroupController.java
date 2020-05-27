package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.ConfigGroup;
import com.javaweb.system.query.ConfigGroupQuery;
import com.javaweb.system.service.IConfigGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 配置分组表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/configgroup")
public class ConfigGroupController extends BaseController {

    @Autowired
    private IConfigGroupService configGroupService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody ConfigGroupQuery query) {
        return configGroupService.getList(query);
    }

    /**
     * 添加配置分组
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "配置分组管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody ConfigGroup entity) {
        return configGroupService.edit(entity);
    }

    /**
     * 编辑配置分组
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "配置分组管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody ConfigGroup entity) {
        return configGroupService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 配置分组ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return configGroupService.info(id);
    }

    /**
     * 删除配置分组
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "配置分组管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody ConfigGroup entity) {
        return configGroupService.delete(entity);
    }


    /**
     * 获取配置分组
     *
     * @return
     */
    @PostMapping("/getGroupList")
    public JsonResult getGroupList() {
        return configGroupService.getGroupList();
    }

}
