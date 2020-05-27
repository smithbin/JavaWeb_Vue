package com.javaweb.system.vo.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.common.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 角色列表Vo
 */
@Data
public class RoleListVo {

    /**
     * 角色ID
     */
    @Excel(name = "角色序号", cellType = Excel.ColumnType.NUMERIC)
    private Integer id;

    /**
     * 角色名称
     */
    @Excel(name = "角色名称")
    private String name;

    /**
     * 排序
     */
    @Excel(name = "角色排序")
    private Integer sort;

    /**
     * 角色拥有的菜单ID，多个规则","隔开
     */
    private String rules;

    /**
     * 规则列表
     */
    private String[] rulesList;

    /**
     * 状态
     */
    @Excel(name = "角色状态", readConverterExp = "1=正常,2=停用")
    private Integer status;

    /**
     * 添加人
     */
    private Integer createUser;

    /**
     * 添加时间
     */
    @Excel(name = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新人
     */
    private Integer updateUser;

    /**
     * 更新时间
     */
    @Excel(name = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
