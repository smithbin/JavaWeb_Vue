package com.javaweb.system.vo.layoutdesc;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 站点描述描述Vo
 */
@Data
public class ItemLayoutDescListVo {

    /**
     * ID
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 站点ID
     */
    private Integer itemId;

    /**
     * 子级
     */
    private List<ItemLayoutDescListVo> children = new ArrayList<>();

}
