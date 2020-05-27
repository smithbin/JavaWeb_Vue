package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.RoleRulesDto;
import com.javaweb.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface IRoleService extends IBaseService<Role> {

    /**
     * 设置角色规则
     *
     * @param roleRulesDto 请求参数
     * @return
     */
    JsonResult setRules(RoleRulesDto roleRulesDto);

    /**
     * 获取角色列表
     *
     * @return
     */
    JsonResult getRoleList();

}
