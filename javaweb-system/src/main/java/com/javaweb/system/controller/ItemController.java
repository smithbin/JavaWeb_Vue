package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Item;
import com.javaweb.system.query.ItemQuery;
import com.javaweb.system.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 站点配置表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private IItemService itemService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody ItemQuery query) {
        return itemService.getList(query);
    }

    /**
     * 添加站点
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "站点管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody Item entity) {
        return itemService.edit(entity);
    }

    /**
     * 更新站点
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "站点管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Item entity) {
        return itemService.edit(entity);
    }

    /**
     * 获取站点详情
     *
     * @param id 站点ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return itemService.info(id);
    }

    /**
     * 删除站点
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "站点管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Item entity) {
        return itemService.delete(entity);
    }

    /**
     * 获取站点类型
     *
     * @return
     */
    @PostMapping("/getItemTypeList")
    public JsonResult getItemTypeList() {
        return itemService.getItemTypeList();
    }

    /**
     * 获取站点列表
     *
     * @return
     */
    @PostMapping("/getItemList")
    public JsonResult getItemList() {
        return itemService.getItemList();
    }

}
