package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.LayoutDesc;
import com.javaweb.system.mapper.LayoutDescMapper;
import com.javaweb.system.query.LayoutDescQuery;
import com.javaweb.system.service.ILayoutDescService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.layoutdesc.LayoutDescListVo;
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
 * 布局描述管理 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class LayoutDescServiceImpl extends BaseServiceImpl<LayoutDescMapper, LayoutDesc> implements ILayoutDescService {

    @Autowired
    private LayoutDescMapper layoutDescMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        LayoutDescQuery layoutDescQuery = (LayoutDescQuery) query;
        // 查询条件
        QueryWrapper<LayoutDesc> queryWrapper = new QueryWrapper<>();
        // 推荐描述名称
        if (!StringUtils.isEmpty(layoutDescQuery.getName())) {
            queryWrapper.like("name", layoutDescQuery.getName());
        }
        queryWrapper.eq("mark", 1);

        // 查询数据
        IPage<LayoutDesc> page = new Page<>(layoutDescQuery.getPageIndex(), layoutDescQuery.getPageSize());
        IPage<LayoutDesc> data = layoutDescMapper.selectPage(page, queryWrapper);
        List<LayoutDesc> layoutDescList = data.getRecords();
        List<LayoutDescListVo> layoutDescListVoList = new ArrayList<>();
        if (!layoutDescList.isEmpty()) {
            layoutDescList.forEach(item -> {
                LayoutDescListVo layoutDescListVo = new LayoutDescListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, layoutDescListVo);
                layoutDescListVoList.add(layoutDescListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", layoutDescListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 添加、编辑推荐描述
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(LayoutDesc entity) {
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
     * 删除推荐描述
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(LayoutDesc entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不能为空");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 获取推荐描述列表
     *
     * @return
     */
    @Override
    public JsonResult getLayoutDescList() {
        QueryWrapper<LayoutDesc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        List<LayoutDesc> layoutDescList = layoutDescMapper.selectList(queryWrapper);
        List<LayoutDescListVo> layoutDescListVoList = new ArrayList<>();
        if (!layoutDescList.isEmpty()) {
            layoutDescList.forEach(item -> {
                LayoutDescListVo layoutDescListVo = new LayoutDescListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, layoutDescListVo);
                layoutDescListVo.setLocDesc(layoutDescListVo.getLocDesc() + "=>" + layoutDescListVo.getLocId());
                layoutDescListVoList.add(layoutDescListVo);
            });
        }
        return JsonResult.success("操作成功", layoutDescListVoList);
    }

    /**
     * 根据站点和位置ID获取布局描述信息
     *
     * @param itemId 站点ID
     * @param locId  位置ID
     * @return
     */
    @Override
    public LayoutDesc getLayoutDescInfo(Integer itemId, Integer locId) {
        QueryWrapper<LayoutDesc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id", itemId);
        queryWrapper.eq("loc_id", locId);
        queryWrapper.eq("mark", 1);
        LayoutDesc layoutDescInfo = this.getOne(queryWrapper);
        return layoutDescInfo;
    }

}
