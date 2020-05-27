package com.javaweb.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.common.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 高德城市表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_city")
public class City extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    /**
     * 添加人
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新人
     */
    private Integer updateUser;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 有效标识（1正常 0删除）
     */
    private Integer mark;


}
