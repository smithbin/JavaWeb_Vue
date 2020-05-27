package com.javaweb.system.dto;

import lombok.Data;

/**
 * 角色权限Dto
 */
@Data
public class RoleRulesDto {

    /**
     * 角色ID
     */
    private Integer id;

    /**
     * 规则
     */
    private String rules;

}
