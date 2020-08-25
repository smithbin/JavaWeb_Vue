package com.javaweb.system.controller;

import com.javaweb.common.common.BaseController;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.LoginDto;
import com.javaweb.system.service.ILoginService;
import com.javaweb.system.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 后台用户管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-02-26
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private ILoginService userService;

    /**
     * 获取登录用户信息
     *
     * @return
     */
    @GetMapping("/getLoginInfo")
    public JsonResult getLoginInfo() {
        return userService.getLoginInfo(ShiroUtils.getAdminId());
    }

    /**
     * 获取验证码
     *
     * @param response
     * @return
     */
    @GetMapping("/captcha")
    public JsonResult captcha(HttpServletResponse response) {
        return userService.captcha(response);
    }

    /**
     * 用户登录
     *
     * @param loginDto 登录Dto
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        return userService.login(loginDto, request);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping("/logout")
    public JsonResult logout() {
        // 退出
        return userService.logout();
    }

    /**
     * 用户未登录
     *
     * @return
     */
    @GetMapping("/un_auth")
    public JsonResult unAuth() {
        return JsonResult.error(HttpStatus.UNAUTHORIZED, "", null);
    }

    /**
     * 用户无权限
     *
     * @return
     */
    @GetMapping("/unauthorized")
    public JsonResult unauthorized() {
        return JsonResult.error(HttpStatus.FORBIDDEN, "用户无权限", null);
    }

    /**
     * 获取菜单列表
     *
     * @return
     */
    @GetMapping("/getMenuList")
    public JsonResult getMenuList() {
        return userService.getMenuList();
    }
}
