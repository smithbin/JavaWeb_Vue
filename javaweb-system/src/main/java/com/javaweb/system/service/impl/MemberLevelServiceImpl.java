package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Member;
import com.javaweb.system.entity.MemberLevel;
import com.javaweb.system.mapper.MemberLevelMapper;
import com.javaweb.system.query.MemberLevelQuery;
import com.javaweb.system.service.IMemberLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.memberlevel.MemberLevelListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员级别表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class MemberLevelServiceImpl extends BaseServiceImpl<MemberLevelMapper, MemberLevel> implements IMemberLevelService {

    @Autowired
    private MemberLevelMapper memberLevelMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        MemberLevelQuery userLevelQuery = (MemberLevelQuery) query;
        // 查询条件
        QueryWrapper<MemberLevel> queryWrapper = new QueryWrapper<>();
        // 级别名称
        if (!StringUtils.isEmpty(userLevelQuery.getName())) {
            queryWrapper.like("name", userLevelQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        IPage<MemberLevel> page = new Page<>(userLevelQuery.getPageIndex(), userLevelQuery.getPageSize());
        IPage<MemberLevel> data = memberLevelMapper.selectPage(page, queryWrapper);
        List<MemberLevel> userLevelList = data.getRecords();
        List<MemberLevelListVo> userLevelListVoList = new ArrayList<>();
        if (!userLevelList.isEmpty()) {
            userLevelList.forEach(item -> {
                MemberLevelListVo userLevelListVo = new MemberLevelListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, userLevelListVo);
                userLevelListVoList.add(userLevelListVo);
            });
        }
        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", userLevelListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 添加、编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(MemberLevel entity) {
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
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(MemberLevel entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不能为空");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

}
