package com.javaweb.system.vo.dictype;

import lombok.Data;

/**
 * 字典类型表单Vo
 */
@Data
public class DicTypeInfoVo {

    /**
     * 字典类型ID
     */
    private Integer id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 标识符
     */
    private String tag;

    /**
     * 显示顺序
     */
    private Integer sort;

}
