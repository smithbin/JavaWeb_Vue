package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Layout;
import com.javaweb.system.query.LayoutQuery;
import com.javaweb.system.service.ILayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 页面布局管理 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/layout")
public class LayoutController extends BaseController {

    @Autowired
    private ILayoutService layoutService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody LayoutQuery query) {
        return layoutService.getList(query);
    }

    /**
     * 添加推荐
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "布局管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody Layout entity) {
        return layoutService.edit(entity);
    }

    /**
     * 编辑推荐
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "布局管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Layout entity) {
        return layoutService.edit(entity);
    }

    /**
     * 获取推荐详情
     *
     * @param id 推荐ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return layoutService.info(id);
    }

    /**
     * 删除推荐
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "布局管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Layout entity) {
        return layoutService.delete(entity);
    }

    /**
     * 获取基础参数
     *
     * @return
     */
    @PostMapping("/getParamList")
    public JsonResult getParamList() {
        return layoutService.getParamList();
    }

}
