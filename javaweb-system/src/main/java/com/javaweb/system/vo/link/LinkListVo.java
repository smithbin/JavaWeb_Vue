package com.javaweb.system.vo.link;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 友链列表Vo
 */
@Data
public class LinkListVo {

    /**
     * 友链ID
     */
    private Integer id;

    /**
     * 站点ID
     */
    private Integer itemId;

    /**
     * 站点名称
     */
    private String itemName;

    /**
     * 栏目ID
     */
    private Integer cateId;

    /**
     * 栏目名称
     */
    private String cateName;

    /**
     * 类型：1友情链接 2合作伙伴
     */
    private Integer type;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 平台：1PC站 2WAP站 3小程序 4APP应用
     */
    private Integer platform;

    /**
     * 友链形式：1文字链接 2图片链接
     */
    private Integer form;

    /**
     * 友链名称
     */
    private String name;

    /**
     * 友链图片
     */
    private String image;

    /**
     * 友链地址
     */
    private String url;

    /**
     * 状态：1在用 2停用
     */
    private Integer status;

    /**
     * 显示顺序
     */
    private Integer sort;

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
