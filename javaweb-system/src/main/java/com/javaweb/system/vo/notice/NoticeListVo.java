package com.javaweb.system.vo.notice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 通知公告列表Vo
 */
@Data
public class NoticeListVo {

    /**
     * 通知ID
     */
    private Integer id;

    /**
     * 通知主题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 来源：1云平台
     */
    private Integer source;

    /**
     * 来源描述
     */
    private String sourceName;

    /**
     * 是否置顶：1是 2否
     */
    private Integer isTop;

    /**
     * 阅读量
     */
    private Integer viewNum;

    /**
     * 是否已发布：1是 2否
     */
    private Integer status;

    /**
     * 发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    /**
     * 是否已推送：1是 2否
     */
    private Integer isSend;

    /**
     * 推送时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

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
