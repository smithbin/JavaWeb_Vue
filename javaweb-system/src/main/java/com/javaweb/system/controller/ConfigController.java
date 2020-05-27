package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Config;
import com.javaweb.system.query.ConfigQuery;
import com.javaweb.system.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 配置表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/config")
public class ConfigController extends BaseController {

    @Autowired
    private IConfigService configService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody ConfigQuery query) {
        return configService.getList(query);
    }

    /**
     * 添加配置
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "配置管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody Config entity) {
        return configService.edit(entity);
    }

    /**
     * 编辑配置
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "配置管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Config entity) {
        return configService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 配置ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return configService.info(id);
    }

    /**
     * 删除配置
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "配置管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Config entity) {
        return configService.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody Config entity) {
        return configService.setStatus(entity);
    }

    /**
     * 获取基础参数
     *
     * @return
     */
    @PostMapping("/getParamList")
    public JsonResult getParamList() {
        return configService.getParamList();
    }

}
