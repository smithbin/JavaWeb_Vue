package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 用户搜索条件
 */
@Data
public class MemberQuery extends BaseQuery {

    /**
     * 手机号码
     */
    private String mobile;

}
