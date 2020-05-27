package com.javaweb.system.vo.itemcate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 栏目列表Vo
 */
@Data
public class ItemCateListVo {

    /**
     * 栏目ID
     */
    private Integer id;

    /**
     * 站点编号
     */
    private Integer itemId;

    /**
     * 站点名称
     */
    private String itemName;

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

    /**
     * 添加用户
     */
    private Integer createUser;

    /**
     * 添加时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新用户
     */
    private Integer updateUser;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 子级数据
     */
    private List<ItemCateListVo> children;

}
