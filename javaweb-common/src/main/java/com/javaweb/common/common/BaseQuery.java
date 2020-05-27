package com.javaweb.common.common;

import lombok.Data;

/**
 * 查询对象基类
 */
@Data
public class BaseQuery {
    /**
     * 页码
     */
    private Integer pageIndex;

    /**
     * 每页数
     */
    private Integer pageSize;
}
