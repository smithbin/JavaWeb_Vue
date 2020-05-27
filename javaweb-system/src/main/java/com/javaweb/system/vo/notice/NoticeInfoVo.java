package com.javaweb.system.vo.notice;

import lombok.Data;

/**
 * 通知公告表单Vo
 */
@Data
public class NoticeInfoVo {

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
     * 是否已推送：1是 2否
     */
    private Integer isSend;

}
