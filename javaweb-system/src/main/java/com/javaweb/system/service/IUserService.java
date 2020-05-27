package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.UserRulesDto;
import com.javaweb.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户管理表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface IUserService extends IBaseService<User> {

    /**
     * 设置人员权限
     *
     * @param adminRulesDto 请求参数
     * @return
     */
    JsonResult setRules(UserRulesDto adminRulesDto);

    /**
     * 重置密码
     *
     * @param entity 实体对象
     * @return
     */
    JsonResult resetPwd(User entity);

}
