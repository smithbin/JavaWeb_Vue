package com.javaweb.system.vo.crontab;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 定时任务列表Vo
 */
@Data
public class CrontabListVo {

    /**
     * 任务ID
     */
    private Integer id;

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

}
