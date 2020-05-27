package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.City;
import com.javaweb.system.query.CityQuery;
import com.javaweb.system.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 高德城市表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/city")
public class CityController extends BaseController {

    @Autowired
    private ICityService cityService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody CityQuery query) {
        return cityService.getList(query);
    }

    /**
     * 添加城市
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "城市管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody City entity) {
        return cityService.edit(entity);
    }

    /**
     * 更新城市
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "城市管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody City entity) {
        return cityService.edit(entity);
    }

    /**
     * 获取城市详情
     *
     * @param id 城市ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return cityService.info(id);
    }

    /**
     * 删除城市
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "城市管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody City entity) {
        return cityService.delete(entity);
    }

}
