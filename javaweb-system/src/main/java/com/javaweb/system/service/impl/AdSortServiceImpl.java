package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.constant.ConfigConstant;
import com.javaweb.system.entity.AdSort;
import com.javaweb.system.mapper.AdSortMapper;
import com.javaweb.system.query.AdSortQuery;
import com.javaweb.system.service.IAdSortService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.adsort.AdSortListVo;
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
 * 广告位管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class AdSortServiceImpl extends BaseServiceImpl<AdSortMapper, AdSort> implements IAdSortService {

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
        AdSortQuery adSortQuery = (AdSortQuery) query;
        // 查询条件
        QueryWrapper<AdSort> queryWrapper = new QueryWrapper<>();
        // 广告位名称
        if (!StringUtils.isEmpty(adSortQuery.getName())) {
            queryWrapper.like("name", adSortQuery.getName());
        }
        queryWrapper.eq("mark", 1);

        // 查询数据
        IPage<AdSort> page = new Page<>(adSortQuery.getPageIndex(), adSortQuery.getPageSize());
        IPage<AdSort> data = adSortMapper.selectPage(page, queryWrapper);
        List<AdSort> adSortList = data.getRecords();
        List<AdSortListVo> adSortListVoList = new ArrayList<>();
        if (!adSortList.isEmpty()) {
            adSortList.forEach(item -> {
                AdSortListVo adSortListVo = new AdSortListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, adSortListVo);
                adSortListVoList.add(adSortListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", adSortListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 添加、编辑广告位
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(AdSort entity) {
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
     * 删除广告位
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(AdSort entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不存在");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 获取参数列表
     *
     * @return
     */
    @Override
    public JsonResult getParamList() {
        Map<String, Object> result = new HashMap<>();
        result.put("platform_list", ConfigConstant.LINK_PLATFORM_LIST);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 获取广告位列表
     *
     * @return
     */
    @Override
    public JsonResult getSortList() {
        // 查询条件
        QueryWrapper<AdSort> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        List<AdSort> adSortList = adSortMapper.selectList(queryWrapper);
        return JsonResult.success("操作成功", adSortList);
    }

}
