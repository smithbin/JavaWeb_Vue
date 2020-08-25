package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.RoleRulesDto;
import com.javaweb.system.entity.Role;
import com.javaweb.system.entity.RoleMenu;
import com.javaweb.system.mapper.RoleMapper;
import com.javaweb.system.mapper.RoleMenuMapper;
import com.javaweb.system.query.RoleQuery;
import com.javaweb.system.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.role.RoleInfoVo;
import com.javaweb.system.vo.role.RoleListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private Validator validator;

    /**
     * 获取角色列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        RoleQuery roleQuery = (RoleQuery) query;
        // 查询条件
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        // 角色名称
        if (!StringUtils.isEmpty(roleQuery.getName())) {
            queryWrapper.like("name", roleQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Role> page = new Page<>(roleQuery.getPageIndex(), roleQuery.getPageSize());
        IPage<Role> data = roleMapper.selectPage(page, queryWrapper);
        List<Role> roleList = data.getRecords();
        List<RoleListVo> roleListVoList = new ArrayList<>();
        if (!roleList.isEmpty()) {
            roleList.forEach(item -> {
                RoleListVo roleListVo = new RoleListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, roleListVo);
                // 角色权限
                String[] rulesList = new String[0];
                if (!StringUtils.isEmpty(item.getRules())) {
                    rulesList = item.getRules().split(",");
                }
                roleListVo.setRulesList(rulesList);
                roleListVoList.add(roleListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", roleListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 获取角色信息
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Role entity = (Role) super.getInfo(id);
        // 设置Vo
        RoleInfoVo roleInfoVo = new RoleInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, roleInfoVo);
        return roleInfoVo;
    }

    /**
     * 添加或编辑角色信息
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Role entity) {
        // 字段校验
        Set<ConstraintViolation<Role>> violationSet = validator.validate(entity);
        for (ConstraintViolation<Role> item : violationSet) {
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
     * 删除角色
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(Role entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不能为空");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 设置角色权限
     *
     * @param roleRulesDto 请求参数
     * @return
     */
    @Override
    public JsonResult setRules(RoleRulesDto roleRulesDto) {
        // 角色ID
        if (roleRulesDto.getId() == null || roleRulesDto.getId() <= 0) {
            return JsonResult.error("角色ID不能为空");
        }
        if (StringUtils.isEmpty(roleRulesDto.getRules())) {
            return JsonResult.error("请选择权限菜单");
        }

        // 保存数据(逗号分隔)
        Role entity = new Role();
        entity.setId(roleRulesDto.getId());
        entity.setRules(roleRulesDto.getRules());
        boolean result = this.updateById(entity);
        if (!result) {
            return JsonResult.error("权限设置失败");
        }

        // 删除已存在的数据
        roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleRulesDto.getId()));

        // 插入角色菜单关系表数据
        Integer totalNum = 0;
        String[] strings = roleRulesDto.getRules().split(",");
        for (String string : strings) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleRulesDto.getId());
            roleMenu.setMenuId(Integer.valueOf(string));
            Integer result2 = roleMenuMapper.insert(roleMenu);
            if (result2 == 1) {
                totalNum++;
            }
        }
        if (totalNum == strings.length) {
            return JsonResult.success("权限设置成功");
        }
        return JsonResult.error("权限设置失败");
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @Override
    public JsonResult getRoleList() {
        // 查询条件
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("mark", 1);

        // 查询数据
        List<Role> roleList = roleMapper.selectList(queryWrapper);
        return JsonResult.success("操作成功", roleList);
    }

    /**
     * 导出Excel
     *
     * @return
     */
    @Override
    public List<Role> exportExcel() {
        // 查询条件
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("mark", 1);

        // 查询数据
        List<Role> roleList = roleMapper.selectList(queryWrapper);
        return roleList;
    }
}
