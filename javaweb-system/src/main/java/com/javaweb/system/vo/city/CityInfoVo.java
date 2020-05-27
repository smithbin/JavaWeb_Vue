package com.javaweb.system.vo.city;

import lombok.Data;

/**
 * 城市表单Vo
 */
@Data
public class CityInfoVo {

    /**
     * 城市ID
     */
    private Integer id;

    /**
     * 父级编号
     */
    private Integer pid;

    /**
     * 城市级别（1省；2市；3区；）
     */
    private Integer level;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 城市编号（区号）
     */
    private String citycode;

    /**
     * 父级地理编号
     */
    private String pAdcode;

    /**
     * 地理编号
     */
    private String adcode;

    /**
     * 城市坐标中心点经度（* 1e6）：如果是中国，此值是 1e7
     */
    private Integer lng;

    /**
     * 城市坐标中心点纬度（* 1e6）
     */
    private Integer lat;

    /**
     * 是否开放1是2否
     */
    private Integer isOpen;

    /**
     * 排序号
     */
    private Integer sort;

}
