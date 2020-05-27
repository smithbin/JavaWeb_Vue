package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.LayoutDesc;
import com.javaweb.system.query.LayoutDescQuery;
import com.javaweb.system.service.ILayoutDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 布局描述管理 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/layoutdesc")
public class LayoutDescController extends BaseController {

    @Autowired
    private ILayoutDescService layoutDescService;

    /**
     * 获取推荐描述列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody LayoutDescQuery query) {
        return layoutDescService.getList(query);
    }

    /**
     * 添加推荐描述
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "布局描述管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody LayoutDesc entity) {
        return layoutDescService.edit(entity);
    }

    /**
     * 编辑推荐描述
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "布局描述管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody LayoutDesc entity) {
        return layoutDescService.edit(entity);
    }

    /**
     * 获取推荐描述详情
     *
     * @param id 推荐描述ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return layoutDescService.info(id);
    }

    /**
     * 删除推荐描述
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "布局描述管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody LayoutDesc entity) {
        return layoutDescService.delete(entity);
    }

    /**
     * 获取推荐描述列表
     *
     * @return
     */
    @PostMapping("/getLayoutDescList")
    public JsonResult getLayoutDescList() {
        return layoutDescService.getLayoutDescList();
    }

}
