package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 配置分组查询条件
 */
@Data
public class ConfigGroupQuery extends BaseQuery {

    /**
     * 分组名称
     */
    private String name;

}
