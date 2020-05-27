package com.javaweb.system.vo.position;

import lombok.Data;

/**
 * 岗位表单Vo
 */
@Data
public class PositionInfoVo {

    /**
     * 岗位ID
     */
    private Integer id;

    /**
     * 岗位名称
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
