package com.javaweb.system.dto;

import lombok.Data;

/**
 * 人员权限Dto
 */
@Data
public class UserRulesDto {

    /**
     * 角色ID
     */
    private Integer id;

    /**
     * 规则
     */
    private String rules;

}
