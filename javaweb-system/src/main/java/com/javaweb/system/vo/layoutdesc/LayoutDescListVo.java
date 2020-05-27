package com.javaweb.system.vo.layoutdesc;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 推荐列表Vo
 */
@Data
public class LayoutDescListVo {

    /**
     * 推荐ID
     */
    private Integer id;

    /**
     * 推荐名称
     */
    private String name;

    /**
     * 页面编号
     */
    private Integer itemId;

    /**
     * 页面编号名称
     */
    private String itemName;

    /**
     * 位置编号
     */
    private Integer locId;

    /**
     * 页面位置描述
     */
    private String locDesc;

    /**
     * 排序
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
