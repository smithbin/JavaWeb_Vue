package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 职级查询条件
 */
@Data
public class LevelQuery extends BaseQuery {

    /**
     * 职级名称
     */
    private String name;

}
