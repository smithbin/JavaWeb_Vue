package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.system.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 登录日志表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface ILoginLogService extends IBaseService<LoginLog> {

    /**
     * 创建系统登录日志
     *
     * @param loginLog 访问日志对象
     */
    void insertLoginLog(LoginLog loginLog);

}
