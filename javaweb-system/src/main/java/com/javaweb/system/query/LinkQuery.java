package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 友链查询条件
 */
@Data
public class LinkQuery extends BaseQuery {

    /**
     * 友链名称
     */
    private String name;

}
