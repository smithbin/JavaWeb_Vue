package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.LoginDto;
import com.javaweb.system.entity.Menu;
import com.javaweb.system.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 后台用户管理表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-02-26
 */
public interface ILoginService extends IBaseService<User> {

    /**
     * 获取验证码
     *
     * @param response 请求响应
     * @return
     */
    JsonResult captcha(HttpServletResponse response);

    /**
     * 用户登录
     *
     * @param loginDto 登录Dto
     * @return
     */
    JsonResult login(LoginDto loginDto, HttpServletRequest request);

    /**
     * 退出登录
     *
     * @return
     */
    JsonResult logout();

    /**
     * 根据名称获取对象
     *
     * @param username 名称
     * @return
     */
    User getUserByName(String username);

    /**
     * 获取菜单列表
     *
     * @return
     */
    JsonResult getMenuList();

    /**
     * 获取登录用户信息
     *
     * @param userId 登录用户ID
     * @return
     */
    JsonResult getLoginInfo(Integer userId);

    /**
     * 获取授权权限
     *
     * @return
     */
    List<Menu> getAuthRule();

}
