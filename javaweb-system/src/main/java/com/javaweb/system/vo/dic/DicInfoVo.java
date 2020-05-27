package com.javaweb.system.vo.dic;

import lombok.Data;

/**
 * 字典表单Vo
 */
@Data
public class DicInfoVo {

    /**
     * 字典ID
     */
    private Integer id;

    /**
     * 字典名称
     */
    private String title;

    /**
     * 内部标签
     */
    private String tag;

    /**
     * 字典值
     */
    private String value;

    /**
     * 字典类型ID
     */
    private Integer typeId;

    /**
     * 状态：1在用 2停用
     */
    private Integer status;

    /**
     * 备注
     */
    private String note;

    /**
     * 显示顺序
     */
    private Integer sort;

}
