package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 登录日志查询条件
 */
@Data
public class LoginLogQuery extends BaseQuery {

    /**
     * 登录用户
     */
    private String loginName;

}
