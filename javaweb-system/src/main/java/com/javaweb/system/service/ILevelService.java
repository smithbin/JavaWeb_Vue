package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Level;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 职级表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface ILevelService extends IBaseService<Level> {

    /**
     * 获取职级列表
     *
     * @return
     */
    JsonResult getLevelList();

}
