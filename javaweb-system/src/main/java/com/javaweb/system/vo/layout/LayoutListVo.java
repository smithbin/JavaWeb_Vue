package com.javaweb.system.vo.layout;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 推荐列表Vo
 */
@Data
public class LayoutListVo {

    /**
     * 推荐ID
     */
    private Integer id;

    /**
     * 页面编号
     */
    private Integer itemId;

    /**
     * 站点名称
     */
    private String itemName;

    /**
     * 页面的位置
     */
    private Integer locId;

    /**
     * 推荐位置
     */
    private String locName;

    /**
     * 类型：1新闻资讯 2其他
     */
    private Integer type;

    /**
     * 推荐类型名称
     */
    private String typeName;

    /**
     * 对应的类型编号
     */
    private Integer typeId;

    /**
     * 图片路径
     */
    private String image;

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
