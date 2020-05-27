package com.javaweb.system.vo.role;

import lombok.Data;

/**
 * 角色表单Vo
 */
@Data
public class RoleInfoVo {

    /**
     * 角色ID
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

}
