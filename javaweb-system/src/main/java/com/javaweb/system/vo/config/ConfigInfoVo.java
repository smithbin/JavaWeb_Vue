package com.javaweb.system.vo.config;

import lombok.Data;

/**
 * 配置表单Vo
 */
@Data
public class ConfigInfoVo {

    /**
     * 配置ID
     */
    private Integer id;

    /**
     * 配置标题
     */
    private String title;

    /**
     * 配置标签符
     */
    private String tag;

    /**
     * 配置值
     */
    private String value;

    /**
     * 配置项
     */
    private String options;

    /**
     * 配置分组ID
     */
    private Integer groupId;

    /**
     * 配置类型
     */
    private String type;

    /**
     * 状态：1正常 2停用
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 配置说明
     */
    private String note;

}
