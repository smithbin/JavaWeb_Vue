package com.javaweb.system.vo.adsort;

import lombok.Data;

/**
 * 广告位表单Vo
 */
@Data
public class AdSortInfoVo {

    /**
     * 广告位ID
     */
    private Integer id;

    /**
     * 广告位名称
     */
    private String name;

    /**
     * 广告位描述
     */
    private String description;

    /**
     * 站点ID
     */
    private Integer itemId;

    /**
     * 栏目ID
     */
    private Integer cateId;

    /**
     * 广告页面位置
     */
    private Integer locId;

    /**
     * 站点类型：1PC站 2wap站 3其他
     */
    private Integer platform;

    /**
     * 广告位排序
     */
    private Integer sort;

}
