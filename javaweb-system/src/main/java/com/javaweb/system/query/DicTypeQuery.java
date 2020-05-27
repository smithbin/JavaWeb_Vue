package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 字典类型查询条件
 */
@Data
public class DicTypeQuery extends BaseQuery {

    /**
     * 类型名称
     */
    private String name;

}
