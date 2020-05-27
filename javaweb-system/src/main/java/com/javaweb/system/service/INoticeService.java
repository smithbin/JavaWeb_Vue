package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 通知公告表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface INoticeService extends IBaseService<Notice> {

    /**
     * 设置置顶
     *
     * @param entity 实体对象
     * @return
     */
    JsonResult setIsTop(Notice entity);


}
