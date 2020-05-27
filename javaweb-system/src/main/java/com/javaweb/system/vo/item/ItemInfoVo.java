package com.javaweb.system.vo.item;

import lombok.Data;

/**
 * 站点表单Vo
 */
@Data
public class ItemInfoVo {

    /**
     * 站点ID
     */
    private Integer id;

    /**
     * 站点名称
     */
    private String name;

    /**
     * 站点类型:1普通站点 2其他
     */
    private Integer type;

    /**
     * 站点地址
     */
    private String url;

    /**
     * 站点图片地址
     */
    private String image;

    /**
     * 是否二级域名1是 2非
     */
    private Integer isDomain;

    /**
     * 状态：1可用 2不可用
     */
    private Integer status;

    /**
     * 站点备注
     */
    private String note;

    /**
     * 显示顺序
     */
    private Integer sort;

}
