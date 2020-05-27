package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.LayoutDesc;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 布局描述管理 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface ILayoutDescService extends IBaseService<LayoutDesc> {

    /**
     * 获取推荐描述列表
     *
     * @return
     */
    JsonResult getLayoutDescList();

    /**
     * 根据站点和位置编号获取布局描述信息
     *
     * @param itemId 站点ID
     * @param locId  位置ID
     * @return
     */
    LayoutDesc getLayoutDescInfo(Integer itemId, Integer locId);

}
