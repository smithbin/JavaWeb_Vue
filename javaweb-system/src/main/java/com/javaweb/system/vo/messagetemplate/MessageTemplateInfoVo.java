package com.javaweb.system.vo.messagetemplate;

import lombok.Data;

/**
 * 消息模板表单Vo
 */
@Data
public class MessageTemplateInfoVo {

    /**
     * 模板ID
     */
    private Integer id;

    /**
     * 模板CODE
     */
    private String code;

    /**
     * 模板标题
     */
    private String title;

    /**
     * 模板类型：1系统模板 2短信模板 3邮件模板 4微信模板
     */
    private Integer type;

    /**
     * 模板内容
     */
    private String content;

    /**
     * 状态：1在用 2停用
     */
    private Integer status;

}
