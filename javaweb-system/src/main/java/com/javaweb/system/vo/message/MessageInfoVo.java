package com.javaweb.system.vo.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 消息表单Vo
 */
@Data
public class MessageInfoVo {

    /**
     * 消息ID
     */
    private Integer id;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 接收人
     */
    private String receiver;

    /**
     * 发送方式：1系统 2短信 3邮件 4微信
     */
    private Integer type;

    /**
     * 发送时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /**
     * 发送状态：1已发送 2未发送
     */
    private Integer sendStatus;

    /**
     * 发送次数
     */
    private Integer sendNum;

    /**
     * 发送备注
     */
    private String note;

}
