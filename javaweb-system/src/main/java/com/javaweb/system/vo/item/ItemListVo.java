package com.javaweb.system.vo.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 站点列表Vo
 */
@Data
public class ItemListVo {

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
     * 站点类型
     */
    private String typeName;

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
