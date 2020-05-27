package com.javaweb.system.vo.level;

import lombok.Data;

/**
 * 职级表单Vo
 */
@Data
public class LevelInfoVo {

    /**
     * 职级ID
     */
    private Integer id;

    /**
     * 职级名称
     */
    private String name;

    /**
     * 状态：1正常 2停用
     */
    private Integer status;

    /**
     * 显示顺序
     */
    private Integer sort;

}
