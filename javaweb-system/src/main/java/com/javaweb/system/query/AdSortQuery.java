package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 广告描述查询条件
 */
@Data
public class AdSortQuery extends BaseQuery {

    /**
     * 广告位名称
     */
    private String name;

}
