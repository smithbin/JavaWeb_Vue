package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.ExcelUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.RoleRulesDto;
import com.javaweb.system.entity.Role;
import com.javaweb.system.query.RoleQuery;
import com.javaweb.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

import java.util.List;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    /**
     * 获取角色列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody RoleQuery query) {
        return roleService.getList(query);
    }

    /**
     * 新增角色
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "角色管理-新增角色", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody Role entity) {
        return roleService.edit(entity);
    }

    /**
     * 获取角色详情
     *
     * @param id 角色ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return roleService.info(id);
    }

    /**
     * 修改角色
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "角色管理-修改角色", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Role entity) {
        return roleService.edit(entity);
    }

    /**
     * 根据ID删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "角色管理-删除角色", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Role entity) {
        return roleService.delete(entity);
    }

    /**
     * 导出Excel
     *
     * @return
     */
    @Log(title = "角色管理-导出Excel", businessType = BusinessType.EXPORT)
    @GetMapping("/exportExcel")
    public JsonResult exportExcel() {
        List<Role> roleList = roleService.exportExcel();
        ExcelUtils<Role> excelUtils = new ExcelUtils<Role>(Role.class);
        return excelUtils.exportExcel(roleList, "角色列表");
    }

    /**
     * 设置角色权限
     *
     * @param roleRulesDto 请求参数
     * @return
     */
    @Log(title = "角色管理-设置权限", businessType = BusinessType.UPDATE)
    @PostMapping("/setRules")
    public JsonResult setRules(@RequestBody RoleRulesDto roleRulesDto) {
        return roleService.setRules(roleRulesDto);
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @PostMapping("/getRoleList")
    public JsonResult getRoleList() {
        return roleService.getRoleList();
    }

}
