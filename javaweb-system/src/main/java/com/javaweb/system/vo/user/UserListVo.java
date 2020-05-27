package com.javaweb.system.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 人员列表Vo
 */
@Data
public class UserListVo {

    /**
     * 人员ID
     */
    private Integer id;

    /**
     * 工号
     */
    private String workNum;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别:1男 2女 3保密
     */
    private Integer gender;

    /**
     * 性别描述
     */
    private String genderName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 公司ID
     */
    private Integer companyId;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 职级ID
     */
    private Integer levelId;

    /**
     * 职级名称
     */
    private String levelName;

    /**
     * 岗位ID
     */
    private Integer positionId;

    /**
     * 岗位名称
     */
    private String positionName;

    /**
     * 省份ID
     */
    private Integer provinceId;

    /**
     * 市区ID
     */
    private Integer cityId;

    /**
     * 区县ID
     */
    private Integer districtId;

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 盐加密
     */
    private String salt;

    /**
     * 状态：1正常 2禁用
     */
    private Integer status;

    /**
     * 人员独立权限的菜单ID，多个规则逗号“,”隔开
     */
    private String rules;

    /**
     * 规则列表
     */
    private String[] rulesList;

    /**
     * 用户角色ID(多个规则逗号“,”隔开)
     */
    private String roleIds;

    /**
     * 备注
     */
    private String note;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 登录次数
     */
    private Integer loginNum;

    /**
     * 最近登录IP
     */
    private String loginIp;

    /**
     * 最近登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    /**
     * 添加人
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新人
     */
    private Integer updateUser;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
