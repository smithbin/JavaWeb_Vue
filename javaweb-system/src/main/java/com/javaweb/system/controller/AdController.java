package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Ad;
import com.javaweb.system.query.AdQuery;
import com.javaweb.system.service.IAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 广告管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/ad")
public class AdController extends BaseController {

    @Autowired
    private IAdService adService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody AdQuery query) {
        return adService.getList(query);
    }

    /**
     * 添加广告
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "广告管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody Ad entity) {
        return adService.edit(entity);
    }

    /**
     * 编辑广告
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "广告管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Ad entity) {
        return adService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 广告ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return adService.info(id);
    }

    /**
     * 删除广告
     *
     * @param ids 实体对象
     * @return
     */
    @Log(title = "广告管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody String ids) {
        return adService.deleteByIds(ids);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody Ad entity) {
        return adService.setStatus(entity);
    }

}
