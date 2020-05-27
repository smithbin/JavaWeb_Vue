package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Position;
import com.javaweb.system.query.PositionQuery;
import com.javaweb.system.service.IPositionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 岗位表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/position")
public class PositionController extends BaseController {

    @Autowired
    private IPositionService positionService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
//    @RequiresPermissions("position:list")
    public JsonResult list(@RequestBody PositionQuery query) {
        return positionService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
//    @RequiresPermissions("position:add")
    public JsonResult add(@RequestBody Position entity) {
        return positionService.edit(entity);
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @GetMapping("/info")
//    @RequiresPermissions("position:info")
    public JsonResult info(Integer id) {
        return positionService.info(id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
//    @RequiresPermissions("position:edit")
    public JsonResult edit(@RequestBody Position entity) {
        return positionService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @return
     */
    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
//    @RequiresPermissions("position:delete")
    public JsonResult delete(@RequestBody Position entity) {
        return positionService.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody Position entity) {
        return positionService.setStatus(entity);
    }

    /**
     * 获取岗位列表
     *
     * @return
     */
    @PostMapping("/getPositionList")
    public JsonResult getPositionList() {
        return positionService.getPositionList();
    }

}
