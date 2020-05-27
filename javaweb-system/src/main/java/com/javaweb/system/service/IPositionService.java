package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Position;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 岗位表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface IPositionService extends IBaseService<Position> {

    /**
     * 获取岗位列表
     *
     * @return
     */
    JsonResult getPositionList();

}
