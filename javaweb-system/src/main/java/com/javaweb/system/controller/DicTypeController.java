package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.DicType;
import com.javaweb.system.query.DicTypeQuery;
import com.javaweb.system.service.IDicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/dictype")
public class DicTypeController extends BaseController {

    @Autowired
    private IDicTypeService dicTypeService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody DicTypeQuery query) {
        return dicTypeService.getList(query);
    }

    /**
     * 添加字典类型
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "字典类型管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody DicType entity) {
        return dicTypeService.edit(entity);
    }

    /**
     * 更新字典类型
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "字典类型管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody DicType entity) {
        return dicTypeService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 记录ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return dicTypeService.info(id);
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "字典类型管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody DicType entity) {
        return dicTypeService.delete(entity);
    }

    /**
     * 获取字典类型
     *
     * @return
     */
    @PostMapping("/getDicTypeList")
    public JsonResult getDicTypeList() {
        return dicTypeService.getDicTypeList();
    }

}
