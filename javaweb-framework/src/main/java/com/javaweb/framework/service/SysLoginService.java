package com.javaweb.framework.service;

import com.javaweb.common.enums.Constants;
import com.javaweb.common.exception.user.UserNotExistsException;
import com.javaweb.common.utils.MessageUtils;
import com.javaweb.common.utils.RedisUtils;
import com.javaweb.framework.manager.AsyncFactory;
import com.javaweb.framework.manager.AsyncManager;
import com.javaweb.system.entity.User;
import com.javaweb.system.service.ILoginService;
import org.apache.shiro.authc.LockedAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 登录接口 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-02-26
 */
@Service
public class SysLoginService {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public User login(String username, String password) {

        // 用户名和验证码校验
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
//        // 验证码为空校验
//        String captcha = ServletUtils.getRequest().getAttribute("key").toString();
//        if (StringUtils.isEmpty(captcha)) {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
//            throw new CaptchaException();
//        }
//        // 验证码校验
//        if (!captcha.equals("520")) {
//            if (!captcha.toLowerCase().equals(redisUtils.get(captcha).toString().toLowerCase())) {
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(captcha, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
//                throw new CaptchaException();
//            }
//        }

        // 查询用户信息
        User user = loginService.getUserByName(username);
        if (user == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
            throw new UserNotExistsException();
        }
        // 判断用户状态
        if (user.getStatus() != 1) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked")));
            throw new LockedAccountException();
        }

        // 创建登录日志
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        return user;
    }
}
