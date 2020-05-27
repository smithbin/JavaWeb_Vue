package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Item;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 站点配置表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface IItemService extends IBaseService<Item> {

    /**
     * 获取站点类型
     *
     * @return
     */
    JsonResult getItemTypeList();

    /**
     * 获取站点列表
     *
     * @return
     */
    JsonResult getItemList();

}
