package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 部门查询条件
 */
@Data
public class DepQuery extends BaseQuery {

    /**
     * 部门名称
     */
    private String name;

}
