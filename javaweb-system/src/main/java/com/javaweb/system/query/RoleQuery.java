package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 角色查询条件
 */
@Data
public class RoleQuery extends BaseQuery {

    /**
     * 角色名称
     */
    private String name;

}
