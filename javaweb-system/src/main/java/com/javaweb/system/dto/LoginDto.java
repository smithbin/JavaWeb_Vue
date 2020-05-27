package com.javaweb.system.dto;

import lombok.Data;

/**
 * 登录Dto
 */
@Data
public class LoginDto {

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 验证码KEY
     */
    private String key;

}
