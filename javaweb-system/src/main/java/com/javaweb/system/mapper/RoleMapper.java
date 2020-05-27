package com.javaweb.system.mapper;

import com.javaweb.system.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取用户角色列表
     *
     * @param id 用户ID
     * @return
     */
    List<Role> getUserRoleList(Integer id);

}
