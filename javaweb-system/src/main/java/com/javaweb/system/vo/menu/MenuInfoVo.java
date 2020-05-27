package com.javaweb.system.vo.menu;

import lombok.Data;

/**
 * 菜单表单Vo
 */
@Data
public class MenuInfoVo {

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
     * 参数
     */
    private String param;

    /**
     * 父级ID
     */
    private Integer pid;

    /**
     * 类型：1模块 2目录 3菜单 4节点
     */
    private Integer type;

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

}
