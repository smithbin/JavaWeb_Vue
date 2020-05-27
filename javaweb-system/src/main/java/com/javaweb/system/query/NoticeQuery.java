package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 通知公告查询条件
 */
@Data
public class NoticeQuery extends BaseQuery {

    /**
     * 通知标题
     */
    private String title;

}
