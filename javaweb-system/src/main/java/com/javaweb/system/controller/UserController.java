package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.UserRulesDto;
import com.javaweb.system.entity.User;
import com.javaweb.system.query.UserQuery;
import com.javaweb.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 后台用户管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody UserQuery query) {
        return userService.getList(query);
    }

    /**
     * 添加人员
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "人员管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody User entity) {
        return userService.edit(entity);
    }

    /**
     * 更新人员
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "人员管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody User entity) {
        return userService.edit(entity);
    }

    /**
     * 获取人员详情
     *
     * @param id 记录ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return userService.info(id);
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "人员管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody User entity) {
        return userService.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody User entity) {
        return userService.setStatus(entity);
    }

    /**
     * 重置密码
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/resetPwd")
    public JsonResult resetPwd(@RequestBody User entity) {
        return userService.resetPwd(entity);
    }

    /**
     * 设置人员独立权限
     *
     * @param adminRulesDto 请求参数
     * @return
     */
    @PostMapping("/setRules")
    public JsonResult setRules(@RequestBody UserRulesDto adminRulesDto) {
        return userService.setRules(adminRulesDto);
    }

}
