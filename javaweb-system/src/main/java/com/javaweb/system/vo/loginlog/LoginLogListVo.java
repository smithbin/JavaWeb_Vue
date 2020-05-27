package com.javaweb.system.vo.loginlog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 登录日志列表Vo
 */
@Data
public class LoginLogListVo {

    /**
     * 日志ID
     */
    private Integer id;

    /**
     * 日志标题
     */
    private String title;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    /**
     * 登录IP地址
     */
    private String loginIp;

    /**
     * 登录地区
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 状态：0成功 1失败
     */
    @NotNull(message = "状态不能为空")
    private Integer status;

    /**
     * 类型：1登录系统 1退出系统
     */
    @NotNull(message = "类型不能为空")
    private Integer type;

    /**
     * 提示信息
     */
    private String msg;

}
