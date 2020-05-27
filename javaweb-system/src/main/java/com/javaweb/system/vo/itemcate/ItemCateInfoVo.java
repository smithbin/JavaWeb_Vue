package com.javaweb.system.vo.itemcate;

import lombok.Data;

/**
 * 栏目表单Vo
 */
@Data
public class ItemCateInfoVo {

    /**
     * 栏目ID
     */
    private Integer id;

    /**
     * 站点编号
     */
    private Integer itemId;

    /**
     * 父级分类ID
     */
    private Integer pid;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 备注
     */
    private String note;

    /**
     * 拼音(全)
     */
    private String pinyin;

    /**
     * 拼音(简)
     */
    private String code;

    /**
     * 有无封面:1有 2无
     */
    private Integer isCover;

    /**
     * 封面
     */
    private String cover;

    /**
     * 状态：1启用 2停用
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

}
