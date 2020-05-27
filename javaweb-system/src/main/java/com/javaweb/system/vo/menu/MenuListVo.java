package com.javaweb.system.vo.menu;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单列表Vo
 */
@Data
public class MenuListVo {

    /**
     * 菜单ID
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * URL地址
     */
    private String url;

    /**
     * 父级ID
     */
    private Integer pid;

    /**
     * 类型：1模块 2目录 3菜单 4节点
     */
    private Integer type;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 是否显示：1显示 2不显示
     */
    private Integer status;

    /**
     * 是否公共：1是 2否
     */
    private Integer isPublic;

    /**
     * 备注
     */
    private String note;

    /**
     * 显示顺序
     */
    private Integer sort;

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

    /**
     * 子级菜单
     */
    private List<MenuListVo> children = new ArrayList<>();

}
