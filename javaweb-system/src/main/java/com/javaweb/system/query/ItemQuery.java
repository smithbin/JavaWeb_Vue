package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 站点查询条件
 */
@Data
public class ItemQuery extends BaseQuery {

    /**
     * 站点名称
     */
    private String name;

}
