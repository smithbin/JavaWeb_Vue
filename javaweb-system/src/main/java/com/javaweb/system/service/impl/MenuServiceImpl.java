package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.constant.ConfigConstant;
import com.javaweb.system.entity.Menu;
import com.javaweb.system.entity.Role;
import com.javaweb.system.mapper.MenuMapper;
import com.javaweb.system.query.MenuQuery;
import com.javaweb.system.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.menu.MenuInfoVo;
import com.javaweb.system.vo.menu.MenuListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private Validator validator;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        MenuQuery menuQuery = (MenuQuery) query;
        // 查询条件
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", 0);
        // 菜单名称
        if (!StringUtils.isEmpty(menuQuery.getName())) {
            queryWrapper.like("name", menuQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        List<Menu> menuList = menuMapper.selectList(queryWrapper);
        List<MenuListVo> menuListVoList = new ArrayList<>();
        if (!menuList.isEmpty()) {
            menuList.forEach(item -> {
                MenuListVo menuListVo = new MenuListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, menuListVo);
                // 获取类型名称
                menuListVo.setTypeName(ConfigConstant.MENU_TYPE_LIST.get(item.getType()));
                // 获取子级
                menuListVo.setChildren(this.getChildMenuList(item.getId()));
                menuListVoList.add(menuListVo);
            });
        }
        return JsonResult.success("操作成功", menuListVoList);
    }

    /**
     * 根据父级ID获取子级菜单
     *
     * @param pid 父级ID
     * @return
     */
    private List<MenuListVo> getChildMenuList(Integer pid) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        List<MenuListVo> menuListVoList = new ArrayList<>();
        List<Menu> menuList = menuMapper.selectList(queryWrapper);
        if (!menuList.isEmpty()) {
            menuList.forEach(item -> {
                // 菜单列表Vo
                MenuListVo menuListVo = new MenuListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, menuListVo);
                // 获取类型名称
                menuListVo.setTypeName(ConfigConstant.MENU_TYPE_LIST.get(item.getType()));
                // 获取子级
                List<MenuListVo> childrenMenuList = this.getChildMenuList(item.getId());
                if (childrenMenuList != null) {
                    menuListVo.setChildren(childrenMenuList);
                }
                menuListVoList.add(menuListVo);
            });
        }
        return menuListVoList;
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Menu entity = (Menu) super.getInfo(id);

        // 详情Vo
        MenuInfoVo menuInfoVo = new MenuInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, menuInfoVo);
        return menuInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Menu entity) {
        // 字段校验
        Set<ConstraintViolation<Menu>> violationSet = validator.validate(entity);
        for (ConstraintViolation<Menu> item : violationSet) {
            return JsonResult.error(item.getMessage());
        }
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateUser(1);
            entity.setUpdateTime(DateUtils.now());
        } else {
            entity.setCreateUser(1);
            entity.setCreateTime(DateUtils.now());
        }
        return super.edit(entity);
    }

    /**
     * 删除菜单
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(Menu entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不能为空");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

}
