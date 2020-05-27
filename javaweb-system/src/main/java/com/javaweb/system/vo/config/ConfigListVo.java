package com.javaweb.system.vo.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 配置列表Vo
 */
@Data
public class ConfigListVo {

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
     * 配置分组名称
     */
    private String groupName;

    /**
     * 配置类型
     */
    private String type;

    /**
     * 配置类型名称
     */
    private String typeName;

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

    /**
     * 添加人
     */
    private Integer createUser;

    /**
     * 添加时间
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
