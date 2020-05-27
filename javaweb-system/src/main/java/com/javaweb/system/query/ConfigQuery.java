package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 配置查询条件
 */
@Data
public class ConfigQuery extends BaseQuery {

    /**
     * 配置标题
     */
    private String title;

}
