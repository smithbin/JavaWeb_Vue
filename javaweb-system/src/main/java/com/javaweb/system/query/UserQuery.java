package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 人员查询条件
 */
@Data
public class UserQuery extends BaseQuery {

    /**
     * 真实姓名
     */
    private String realname;

}
