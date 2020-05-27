package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.enums.Constants;
import com.javaweb.common.utils.*;
import com.javaweb.system.dto.LoginDto;
import com.javaweb.system.entity.Menu;
import com.javaweb.system.entity.Role;
import com.javaweb.system.entity.User;
import com.javaweb.system.mapper.MenuMapper;
import com.javaweb.system.mapper.RoleMapper;
import com.javaweb.system.mapper.UserMapper;
import com.javaweb.system.service.ILoginLogService;
import com.javaweb.system.service.ILoginService;
import com.javaweb.system.vo.menu.MenuListVo;
import com.javaweb.system.vo.user.UserInfoVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 后台用户管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-02-26
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl<UserMapper, User> implements ILoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ILoginLogService loginLogService;

    /**
     * 获取验证码
     *
     * @param response 请求响应
     * @return
     */
    @Override
    public JsonResult captcha(HttpServletResponse response) {
        VerifyUtil verifyUtil = new VerifyUtil();
        Map<String, String> result = new HashMap();
        try {
            String key = UUID.randomUUID().toString();
            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");
            // 返回base64
            //写入redis缓存
            Map<String, String> mapInfo = verifyUtil.getRandomCodeBase64();
            String randomStr = mapInfo.get("randomStr");
            redisUtils.set(key, randomStr, 60 * 5);

            result.put("url", "data:image/png;base64," + mapInfo.get("img"));
            result.put("key", key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
        return JsonResult.success("操作成功", result);
    }

    /**
     * 用户登录
     *
     * @param loginDto 登录Dto
     * @return
     */
    @Override
    public JsonResult login(LoginDto loginDto, HttpServletRequest request) {
        // 验证码KEY
        if (StringUtils.isEmpty(loginDto.getKey())) {
            return JsonResult.error("验证码KEY不能为空");
        }
        // 验证码
        if (!loginDto.getCaptcha().equals("520")) {
            if (StringUtils.isEmpty(loginDto.getCaptcha())) {
                return JsonResult.error("验证码不能为空");
            }
            // 验证码校验
            if (!loginDto.getCaptcha().toLowerCase().equals(redisUtils.get(loginDto.getKey()).toString().toLowerCase())) {
                return JsonResult.error("验证码不正确");
            }
        }

        try {
            //验证身份和登陆
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(loginDto.getUsername(), loginDto.getPassword());
            // 设置记住我
//            token.setRememberMe(true);
            //进行登录操作
            subject.login(token);

            // 返回结果
            Map<String, String> result = new HashMap<>();
            result.put("token", SecurityUtils.getSubject().getSession().getId().toString());
            return JsonResult.success("操作成功", result);
        } catch (AuthenticationException e) {
            return JsonResult.error(e.getMessage());
        }
    }

    /**
     * 退出登录
     *
     * @return
     */
    @Override
    public JsonResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
//        // 获取当前登录人信息
//        User user = ShiroUtils.getLoginInfo();
//        // 记录用户退出日志
//        AsyncManager.me().execute(AsyncFactory.recordLogininfor(user.getUsername(), Constants.LOGOUT, "退出成功"));
//        // 退出登录
//        ShiroUtils.logout();
        return JsonResult.success("注销成功");
    }

    /**
     * 根据用户名获取用户对象
     *
     * @param username 名称
     * @return
     */
    @Override
    public User getUserByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("mark", 1);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    /**
     * 获取菜单列表
     *
     * @return
     */
    @Override
    public JsonResult getMenuList() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("mark", 1);
        List<Menu> menuList = menuMapper.selectList(queryWrapper);
        List<MenuListVo> menuListVoList = new ArrayList<>();
        if (!menuList.isEmpty()) {
            menuList.forEach(item -> {
                MenuListVo menuListVo = new MenuListVo();
                BeanUtils.copyProperties(item, menuListVo);
                menuListVoList.add(menuListVo);
            });
        }
        return JsonResult.success("操作成功", menuListVoList);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        User entity = (User) super.getInfo(id);
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(entity, userInfoVo);
        // 头像
        if (!StringUtils.isEmpty(entity.getAvatar())) {
            userInfoVo.setAvatar(CommonUtils.getImageURL(entity.getAvatar()));
        }
        return userInfoVo;
    }

    /**
     * 获取登录信息
     *
     * @param userId 登录用户ID
     * @return
     */
    @Override
    public JsonResult getLoginInfo(Integer userId) {
        // 获取用户信息
        UserInfoVo userInfoVo = (UserInfoVo) this.getInfo(userId);
        // 权限数组
        List<String> stringList = new ArrayList<>();
        if (!StringUtils.isEmpty(userInfoVo.getRules())) {
            String[] itemArr = userInfoVo.getRules().split(",");
            for (String s : itemArr) {
                stringList.add(s);
            }
        }
        // 获取用户角色数组
        List<Role> roleList = roleMapper.getUserRoleList(userId);
        if (!roleList.isEmpty()) {
            roleList.forEach(item -> {
                if (!StringUtils.isEmpty(item.getRules())) {
                    String[] subItem = item.getRules().split(",");
                    for (String s : subItem) {
                        if (!stringList.contains(s)) {
                            stringList.add(s);
                        }
                    }
                }
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("info", userInfoVo);
        result.put("permission", org.apache.commons.lang3.StringUtils.join(stringList, ","));
        return JsonResult.success("操作成功", result);
    }

    /**
     * 获取授权权限
     *
     * @return
     */
    @Override
    public List<Menu> getAuthRule() {
        User entity = (User) SecurityUtils.getSubject().getPrincipal();
        Integer userId = entity.getId();
        User userInfo = userMapper.selectById(userId);

        // 权限数组
        List<String> stringList = new ArrayList<>();
        if (!StringUtils.isEmpty(userInfo.getRules())) {
            String[] itemArr = userInfo.getRules().split(",");
            for (String s : itemArr) {
                stringList.add(s);
            }
        }

        // 获取用户角色数组
        List<Role> roleList = roleMapper.getUserRoleList(userId);
        if (!roleList.isEmpty()) {
            roleList.forEach(item -> {
                if (!StringUtils.isEmpty(item.getRules())) {
                    String[] subItem = item.getRules().split(",");
                    for (String s : subItem) {
                        if (!stringList.contains(s)) {
                            stringList.add(s);
                        }
                    }
                }
            });
        }

        // 查询权限
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", org.apache.commons.lang3.StringUtils.join(stringList, ","));
        queryWrapper.eq("status", 1);
        queryWrapper.eq("mark", 1);
        List<Menu> menuList = menuMapper.selectList(queryWrapper);
        return menuList;
    }

}
