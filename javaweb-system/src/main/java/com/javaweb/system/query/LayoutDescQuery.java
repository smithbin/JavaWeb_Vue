package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 推荐查询条件
 */
@Data
public class LayoutDescQuery extends BaseQuery {

    /**
     * 推荐位名称
     */
    private String name;

}
