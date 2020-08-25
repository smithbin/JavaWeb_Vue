package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.UserRulesDto;
import com.javaweb.system.entity.Level;
import com.javaweb.system.entity.Position;
import com.javaweb.system.entity.User;
import com.javaweb.system.entity.UserRole;
import com.javaweb.system.mapper.LevelMapper;
import com.javaweb.system.mapper.PositionMapper;
import com.javaweb.system.mapper.UserMapper;
import com.javaweb.system.mapper.UserRoleMapper;
import com.javaweb.system.query.UserQuery;
import com.javaweb.system.service.IDepService;
import com.javaweb.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.user.UserInfoVo;
import com.javaweb.system.vo.user.UserListVo;
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
 * 后台用户管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Validator validator;
    @Autowired
    private LevelMapper levelMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private IDepService depService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        UserQuery userQuery = (UserQuery) query;
        // 查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 人员姓名
        if (!StringUtils.isEmpty(userQuery.getRealname())) {
            queryWrapper.like("realname", userQuery.getRealname());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        IPage<User> page = new Page<>(userQuery.getPageIndex(), userQuery.getPageSize());
        IPage<User> data = userMapper.selectPage(page, queryWrapper);
        List<User> adminList = data.getRecords();
        List<UserListVo> adminListVoList = new ArrayList<>();
        if (!adminList.isEmpty()) {
            adminList.forEach(item -> {
                UserListVo adminListVo = new UserListVo();
                // 复制属性
                BeanUtils.copyProperties(item, adminListVo);
                // 头像
                adminListVo.setAvatar(CommonUtils.getImageURL(item.getAvatar()));
                // 获取职级
                Level levelInfo = levelMapper.selectById(item.getLevelId());
                if (levelInfo != null) {
                    adminListVo.setLevelName(levelInfo.getName());
                }
                // 获取岗位
                Position positionInfo = positionMapper.selectById(item.getPositionId());
                if (positionInfo != null) {
                    adminListVo.setPositionName(positionInfo.getName());
                }
                // 获取部门
                String depName = depService.getDeptName(item.getDeptId());
                adminListVo.setDeptName(depName);
                // 独立权限
                String[] rulesList = new String[0];
                if (!StringUtils.isEmpty(item.getRules())) {
                    rulesList = item.getRules().split(",");
                }
                adminListVo.setRulesList(rulesList);
                adminListVoList.add(adminListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", adminListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 获取详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        User entity = (User) super.getInfo(id);
        // 头像处理
        if (!StringUtils.isEmpty(entity.getAvatar())) {
            entity.setAvatar(CommonUtils.getImageURL(entity.getAvatar()));
        }
        UserInfoVo adminInfoVo = new UserInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, adminInfoVo);
        return adminInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(User entity) {
        // 字段校验
        Set<ConstraintViolation<User>> violationSet = validator.validate(entity);
        for (ConstraintViolation<User> item : violationSet) {
            return JsonResult.error(item.getMessage());
        }
        // 头像处理
        if (!StringUtils.isEmpty(entity.getAvatar()) && entity.getAvatar().contains(CommonConfig.imageURL)) {
            entity.setAvatar(entity.getAvatar().replaceAll(CommonConfig.imageURL, ""));
        }
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateUser(1);
            entity.setUpdateTime(DateUtils.now());
        } else {
            // 添加用户时默认密码：123456
            entity.setPassword(CommonUtils.password("123456"));
            entity.setCreateUser(1);
            entity.setCreateTime(DateUtils.now());
        }
        boolean result = this.saveOrUpdate(entity);
        if (!result) {
            return JsonResult.error();
        }

        // 删除现存的人员角色关系数据
        userRoleMapper.delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, entity.getId()));
        // 新增人员角色关系表
        if (!StringUtils.isEmpty(entity.getRoleIds())) {
            String[] strings = entity.getRoleIds().split(",");
            for (String string : strings) {
                UserRole userRole = new UserRole();
                userRole.setUserId(entity.getId());
                userRole.setRoleId(Integer.valueOf(string));
                Integer result2 = userRoleMapper.insert(userRole);
            }
        }
        return JsonResult.success();
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(User entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不能为空");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult setStatus(User entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("人员ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("人员状态不能为空");
        }
        return super.setStatus(entity);
    }

    /**
     * 重置密码
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult resetPwd(User entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("人员ID不能为空");
        }
        entity.setPassword(CommonUtils.password("123456"));
        return this.update(entity);
    }

    /**
     * 设置人员权限
     *
     * @param adminRulesDto 请求参数
     * @return
     */
    @Override
    public JsonResult setRules(UserRulesDto adminRulesDto) {
        // 人员ID
        if (adminRulesDto.getId() == null || adminRulesDto.getId() <= 0) {
            return JsonResult.error("人员ID不能为空");
        }
        // 保存数据
        User entity = new User();
        entity.setId(adminRulesDto.getId());
        entity.setRules(adminRulesDto.getRules());
        boolean result = this.updateById(entity);
        if (!result) {
            return JsonResult.error("权限设置失败");
        }
        return JsonResult.success("权限设置成功");
    }

}
