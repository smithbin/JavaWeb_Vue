package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 消息模板查询条件
 */
@Data
public class MessageTemplateQuery extends BaseQuery {

    /**
     * 模板标题
     */
    private String title;

}
