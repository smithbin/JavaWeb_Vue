package com.javaweb.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.common.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 页面布局管理
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_layout")
public class Layout extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 页面编号
     */
    private Integer itemId;

    /**
     * 页面的位置
     */
    private Integer locId;

    /**
     * 类型：1新闻资讯 2其他
     */
    private Integer type;

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

    /**
     * 有效标记
     */
    private Integer mark;


}
