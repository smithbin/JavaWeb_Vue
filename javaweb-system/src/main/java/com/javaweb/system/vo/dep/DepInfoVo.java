package com.javaweb.system.vo.dep;

import lombok.Data;

/**
 * 部门详情Vo
 */
@Data
public class DepInfoVo {

    /**
     * 部门ID
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 是否有子级：1是 2否
     */
    private Integer hasChild;

    /**
     * 上级ID
     */
    private Integer pid;

    /**
     * 排序
     */
    private Integer sort;

}
