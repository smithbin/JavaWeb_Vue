package com.javaweb.system.vo.layout;

import lombok.Data;

/**
 * 推荐表单Vo
 */
@Data
public class LayoutInfoVo {

    /**
     * 推荐ID
     */
    private Integer id;

    /**
     * 页面编号
     */
    private Integer itemId;

    /**
     * 页面的位置
     */
    private Integer locId;

    /**
     * 类型：1新闻资讯 2其他
     */
    private Integer type;

    /**
     * 对应的类型编号
     */
    private Integer typeId;

    /**
     * 图片路径
     */
    private String image;

    /**
     * 显示顺序
     */
    private Integer sort;

}
