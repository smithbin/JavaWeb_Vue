package com.javaweb.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.common.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 定时任务表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_crontab")
public class Crontab extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务标题
     */
    private String title;

    /**
     * crontab格式
     */
    private String cron;

    /**
     * 延迟秒数(单位：毫秒)
     */
    private Integer delay;

    /**
     * 间隔秒数(单位：毫秒)
     */
    private Integer fixed;

    /**
     * 已执行次数
     */
    private Integer execute;

    /**
     * 状态：1正常 2暂停
     */
    private Integer status;

    /**
     * 备注
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

    /**
     * 有效标识：1有效 0删除
     */
    private Integer mark;


}
