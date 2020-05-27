package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 定时任务查询条件
 */
@Data
public class CrontabQuery extends BaseQuery {

    /**
     * 任务标题
     */
    private String title;

}
