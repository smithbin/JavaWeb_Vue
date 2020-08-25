package com.javaweb.system.mapper;

import com.javaweb.system.entity.Menu;
import com.javaweb.system.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 获取权限菜单列表
     *
     * @param param 角色列表
     * @return
     */
    List<Menu> getMenuList(@RequestParam("param") Map<String, Object> param);

}
