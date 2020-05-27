package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 会员级别
 */
@Data
public class MemberLevelQuery extends BaseQuery {

    /**
     * 级别名称
     */
    private String name;

}
