package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.ItemCate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 栏目管理表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface IItemCateService extends IBaseService<ItemCate> {

    /**
     * 获取栏目列表
     *
     * @return
     */
    JsonResult getCateList();

    /**
     * 获取栏目名称
     *
     * @param cateId 栏目ID
     * @return
     */
    String getCateName(Integer cateId);

}
