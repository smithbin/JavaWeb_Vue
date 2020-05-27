package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * 字典查询条件
 */
@Data
public class DicQuery extends BaseQuery {

    /**
     * 字典标题
     */
    private String title;

}
