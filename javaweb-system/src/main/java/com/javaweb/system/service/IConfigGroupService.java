package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.ConfigGroup;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 配置分组表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface IConfigGroupService extends IBaseService<ConfigGroup> {

    /**
     * 获取配置分组列表
     *
     * @return
     */
    JsonResult getGroupList();

}
