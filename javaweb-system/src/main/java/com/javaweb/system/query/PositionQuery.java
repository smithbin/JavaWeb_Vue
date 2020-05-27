package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 岗位查询条件
 */
@Data
public class PositionQuery extends BaseQuery {

    /**
     * 岗位名称
     */
    private String name;

}
