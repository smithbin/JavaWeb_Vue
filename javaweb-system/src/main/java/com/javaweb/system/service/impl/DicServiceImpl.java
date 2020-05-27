package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Dic;
import com.javaweb.system.mapper.DicMapper;
import com.javaweb.system.query.DicQuery;
import com.javaweb.system.service.IDicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.dic.DicListVo;
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
 * 字典管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class DicServiceImpl extends BaseServiceImpl<DicMapper, Dic> implements IDicService {

    @Autowired
    private DicMapper dicMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        DicQuery dicQuery = (DicQuery) query;
        // 查询条件
        QueryWrapper<Dic> queryWrapper = new QueryWrapper<>();
        // 字典标题
        if (!StringUtils.isEmpty(dicQuery.getTitle())) {
            queryWrapper.like("title", dicQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        IPage<Dic> page = new Page<>(dicQuery.getPageIndex(), dicQuery.getPageSize());
        IPage<Dic> data = dicMapper.selectPage(page, queryWrapper);
        List<Dic> dicList = data.getRecords();
        List<DicListVo> dicListVoList = new ArrayList<>();
        if (!dicList.isEmpty()) {
            dicList.forEach(item -> {
                DicListVo dicListVo = new DicListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, dicListVo);
                dicListVoList.add(dicListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", dicListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 添加、编辑字典
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Dic entity) {
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
     * 删除字典
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(Dic entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不存在");
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
    public JsonResult setStatus(Dic entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("字典ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("字典状态不能为空");
        }
        return super.setStatus(entity);
    }

}
