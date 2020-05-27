package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.AdSort;
import com.javaweb.system.query.AdSortQuery;
import com.javaweb.system.service.IAdSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 广告位管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/adsort")
public class AdSortController extends BaseController {

    @Autowired
    private IAdSortService adSortService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody AdSortQuery query) {
        return adSortService.getList(query);
    }

    /**
     * 添加广告位
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "广告位管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody AdSort entity) {
        return adSortService.edit(entity);
    }

    /**
     * 编辑广告位
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "广告位管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody AdSort entity) {
        return adSortService.edit(entity);
    }

    /**
     * 获取广告位详情
     *
     * @param id 广告位ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return adSortService.info(id);
    }

    /**
     * 删除广告位
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "广告位管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody AdSort entity) {
        return adSortService.delete(entity);
    }

    /**
     * 获取参数列表
     *
     * @return
     */
    @PostMapping("/getParamList")
    public JsonResult getParamList() {
        return adSortService.getParamList();
    }

    /**
     * 获取广告位列表
     *
     * @return
     */
    @PostMapping("/getSortList")
    public JsonResult getSortList() {
        return adSortService.getSortList();
    }

}
