package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Dic;
import com.javaweb.system.query.DicQuery;
import com.javaweb.system.service.IDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 字典管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/dic")
public class DicController extends BaseController {

    @Autowired
    private IDicService dicService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody DicQuery query) {
        return dicService.getList(query);
    }

    /**
     * 添加字典
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "字典管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody Dic entity) {
        return dicService.edit(entity);
    }

    /**
     * 编辑字典
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "字典管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Dic entity) {
        return dicService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 字典ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return dicService.info(id);
    }

    /**
     * 删除字典
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "字典管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Dic entity) {
        return dicService.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody Dic entity) {
        return dicService.setStatus(entity);
    }

}
