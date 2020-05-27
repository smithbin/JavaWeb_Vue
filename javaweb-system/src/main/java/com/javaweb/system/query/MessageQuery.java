package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 消息查询条件
 */
@Data
public class MessageQuery extends BaseQuery {

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息类型
     */
    private Integer type;

    /**
     * 发送状态：1已发送 2未发送
     */
    private Integer sendStatus;

}
