package com.javaweb.system.vo.link;

import lombok.Data;

/**
 * 友链表单Vo
 */
@Data
public class LinkInfoVo {

    /**
     * 友链ID
     */
    private Integer id;

    /**
     * 站点ID
     */
    private Integer itemId;

    /**
     * 栏目ID
     */
    private Integer cateId;

    /**
     * 类型：1友情链接 2合作伙伴
     */
    private Integer type;

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

}
