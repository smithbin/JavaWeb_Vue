package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 城市查询条件
 */
@Data
public class CityQuery extends BaseQuery {

    /**
     * 城市名称
     */
    private String name;

}
