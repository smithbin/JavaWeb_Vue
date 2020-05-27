package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.ItemCate;
import com.javaweb.system.query.ItemCateQuery;
import com.javaweb.system.service.IItemCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 栏目管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/itemcate")
public class ItemCateController extends BaseController {

    @Autowired
    private IItemCateService itemCateService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody ItemCateQuery query) {
        return itemCateService.getList(query);
    }

    /**
     * 添加栏目
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "栏目管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody ItemCate entity) {
        return itemCateService.edit(entity);
    }

    /**
     * 更新栏目
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "栏目管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody ItemCate entity) {
        return itemCateService.edit(entity);
    }

    /**
     * 获取栏目详情
     *
     * @param id 栏目ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return itemCateService.info(id);
    }

    /**
     * 删除栏目
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "栏目管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody ItemCate entity) {
        return itemCateService.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody ItemCate entity) {
        return itemCateService.setStatus(entity);
    }

    /**
     * 获取栏目列表
     *
     * @return
     */
    @PostMapping("/getCateList")
    public JsonResult getCateList() {
        return itemCateService.getCateList();
    }

}
