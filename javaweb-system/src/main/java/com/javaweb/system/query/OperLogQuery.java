package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 操作日志查询条件
 */
@Data
public class OperLogQuery extends BaseQuery {

    /**
     * 日志标题
     */
    private String title;

}
