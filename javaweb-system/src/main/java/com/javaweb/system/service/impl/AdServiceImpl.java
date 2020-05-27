package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Ad;
import com.javaweb.system.entity.AdSort;
import com.javaweb.system.mapper.AdMapper;
import com.javaweb.system.mapper.AdSortMapper;
import com.javaweb.system.query.AdQuery;
import com.javaweb.system.service.IAdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.ad.AdInfoVo;
import com.javaweb.system.vo.ad.AdListVo;
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
 * 广告管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class AdServiceImpl extends BaseServiceImpl<AdMapper, Ad> implements IAdService {

    @Autowired
    private AdMapper adMapper;

    @Autowired
    private AdSortMapper adSortMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        AdQuery adQuery = (AdQuery) query;
        // 查询条件
        QueryWrapper<Ad> queryWrapper = new QueryWrapper<>();
        // 广告标题
        if (!StringUtils.isEmpty(adQuery.getTitle())) {
            queryWrapper.like("title", adQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        IPage<Ad> page = new Page<>(adQuery.getPageIndex(), adQuery.getPageSize());
        IPage<Ad> data = adMapper.selectPage(page, queryWrapper);
        List<Ad> adList = data.getRecords();
        List<AdListVo> adListVoList = new ArrayList<>();
        if (!adList.isEmpty()) {
            adList.forEach(item -> {
                AdListVo adListVo = new AdListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, adListVo);
                // 获取广告位
                AdSort adSort = adSortMapper.selectById(item.getSortId());
                adListVo.setSortName(adSort.getName());
                adListVoList.add(adListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", adListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Ad entity = (Ad) super.getInfo(id);
        // 初始化Vo
        AdInfoVo adInfoVo = new AdInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, adInfoVo);
        return adInfoVo;
    }

    /**
     * 添加、编辑广告
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Ad entity) {
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
     * 删除广告
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(Ad entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录不存在");
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
    public JsonResult setStatus(Ad entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }

}
