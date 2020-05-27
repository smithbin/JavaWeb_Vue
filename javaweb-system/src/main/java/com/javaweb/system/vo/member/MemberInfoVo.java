package com.javaweb.system.vo.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户表单Vo
 */
@Data
public class MemberInfoVo {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 性别（1男 2女 3未知）
     */
    private Integer gender;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 户籍省份编号
     */
    private Integer provinceId;

    /**
     * 户籍城市编号
     */
    private Integer cityId;

    /**
     * 户籍区/县编号
     */
    private Integer districtId;

    /**
     * 个人简介
     */
    private String intro;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 设备类型：1苹果 2安卓 3WAP站 4PC站 5后台添加
     */
    private Integer device;

    /**
     * 来源：1、APP注册；2、后台添加；
     */
    private Integer source;

    /**
     * 是否启用 1、启用  2、停用
     */
    private Integer status;

}
