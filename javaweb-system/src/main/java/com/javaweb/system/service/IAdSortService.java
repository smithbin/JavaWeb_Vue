package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.AdSort;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 广告位管理表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface IAdSortService extends IBaseService<AdSort> {

    /**
     * 获取广告位列表
     *
     * @return
     */
    JsonResult getSortList();

}
