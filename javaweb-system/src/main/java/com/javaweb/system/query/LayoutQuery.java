package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 推荐查询条件
 */
@Data
public class LayoutQuery extends BaseQuery {

    /**
     * 站点ID
     */
    private Integer itemId;

    /**
     * 位置ID
     */
    private Integer locId;

}
