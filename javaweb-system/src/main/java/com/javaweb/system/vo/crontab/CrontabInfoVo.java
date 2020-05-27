package com.javaweb.system.vo.crontab;

import lombok.Data;

/**
 * 定时任务表单Vo
 */
@Data
public class CrontabInfoVo {

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

}
