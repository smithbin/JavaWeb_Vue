package com.javaweb.system.vo.configgroup;

import lombok.Data;

/**
 * 配置分组表单Vo
 */
@Data
public class ConfigGroupInfoVo {

    /**
     * 分组ID
     */
    private Integer id;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

}
