package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.constant.ConfigConstant;
import com.javaweb.system.entity.Member;
import com.javaweb.system.mapper.MemberMapper;
import com.javaweb.system.query.MemberQuery;
import com.javaweb.system.query.UserQuery;
import com.javaweb.system.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.member.MemberInfoVo;
import com.javaweb.system.vo.member.MemberListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        MemberQuery userQuery = (MemberQuery) query;
        // 查询条件
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        // 手机号码
        if (!StringUtils.isEmpty(userQuery.getMobile())) {
            queryWrapper.like("mobile", userQuery.getMobile());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<Member> page = new Page<>(userQuery.getPageIndex(), userQuery.getPageSize());
        IPage<Member> data = memberMapper.selectPage(page, queryWrapper);
        List<Member> userList = data.getRecords();
        List<MemberListVo> userListVoList = new ArrayList<>();
        if (!userList.isEmpty()) {
            userList.forEach(item -> {
                MemberListVo userListVo = new MemberListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, userListVo);
                userListVoList.add(userListVo);
            });
        }
        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", userListVoList);
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
        Member user = (Member) super.getInfo(id);
        // 头像处理
        if (!StringUtils.isEmpty(user.getAvatar())) {
            user.setAvatar(CommonUtils.getImageURL(user.getAvatar()));
        }
        // 拷贝属性
        MemberInfoVo userInfoVo = new MemberInfoVo();
        BeanUtils.copyProperties(user, userInfoVo);
        return userInfoVo;
    }

    /**
     * 添加、编辑用户
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Member entity) {
        // 头像处理
        if (!StringUtils.isEmpty(entity.getAvatar()) && entity.getAvatar().contains(CommonConfig.imageURL)) {
            entity.setAvatar(entity.getAvatar().replaceAll(CommonConfig.imageURL, ""));
        }
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateUser(1);
            entity.setUpdateTime(DateUtils.now());
        } else {
            entity.setCreateTime(DateUtils.now());
        }
        return super.edit(entity);
    }

    /**
     * 删除用户
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(Member entity) {
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
    public JsonResult setStatus(Member entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("用户ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("用户状态不能为空");
        }
        return super.setStatus(entity);
    }

    /**
     * 获取设备类型
     *
     * @return
     */
    @Override
    public JsonResult getParamList() {
        Map<String, Object> result = new HashMap<>();
        result.put("device_list", ConfigConstant.USER_DEVICE_LIST);
        return JsonResult.success("操作成功", result);
    }

}
