package com.javaweb.system.vo.layoutdesc;

import lombok.Data;

/**
 * 推荐表单Vo
 */
@Data
public class LayoutDescInfoVo {

    /**
     * 推荐ID
     */
    private Integer id;

    /**
     * 推荐名称
     */
    private String name;

    /**
     * 页面编号
     */
    private Integer itemId;

    /**
     * 位置编号
     */
    private Integer locId;

    /**
     * 页面位置描述
     */
    private String locDesc;

    /**
     * 排序
     */
    private Integer sort;

}
