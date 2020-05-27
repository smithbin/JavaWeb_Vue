package com.javaweb.system.vo.memberlevel;

import lombok.Data;

/**
 * 会员级别表单Vo
 */
@Data
public class MemberLevelInfoVo {

    /**
     * 级别ID
     */
    private Integer id;

    /**
     * 级别名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer sort;

}
