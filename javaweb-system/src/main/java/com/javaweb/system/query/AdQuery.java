package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 广告查询条件
 */
@Data
public class AdQuery extends BaseQuery {

    /**
     * 广告标题
     */
    private String title;

}
