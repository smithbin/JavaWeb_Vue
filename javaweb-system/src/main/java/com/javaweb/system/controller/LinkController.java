package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Link;
import com.javaweb.system.query.LinkQuery;
import com.javaweb.system.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 友链管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/link")
public class LinkController extends BaseController {

    @Autowired
    private ILinkService linkService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody LinkQuery query) {
        return linkService.getList(query);
    }

    /**
     * 添加友链
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "友链管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody Link entity) {
        return linkService.edit(entity);
    }

    /**
     * 更新友链
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "友链管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Link entity) {
        return linkService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 友链ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return linkService.info(id);
    }

    /**
     * 删除友链
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "友链管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Link entity) {
        return linkService.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody Link entity) {
        return linkService.setStatus(entity);
    }

    /**
     * 获取参数列表
     *
     * @return
     */
    @PostMapping("/getParamList")
    public JsonResult getParamList() {
        return linkService.getParamList();
    }

}
