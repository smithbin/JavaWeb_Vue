package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.City;
import com.javaweb.system.mapper.CityMapper;
import com.javaweb.system.query.CityQuery;
import com.javaweb.system.service.ICityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.city.CityListVo;
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
 * 高德城市表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class CityServiceImpl extends BaseServiceImpl<CityMapper, City> implements ICityService {

    @Autowired
    private CityMapper cityMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        CityQuery cityQuery = (CityQuery) query;
        // 查询条件
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", 1);
        // 城市名称
        if (!StringUtils.isEmpty(cityQuery.getName())) {
            queryWrapper.like("name", cityQuery.getName());
        }
        queryWrapper.eq("mark", 1);

        // 查询数据
        IPage<City> page = new Page<>(cityQuery.getPageIndex(), cityQuery.getPageSize());
        IPage<City> data = cityMapper.selectPage(page, queryWrapper);
        List<City> cityList = data.getRecords();
        List<CityListVo> cityListVoList = new ArrayList<>();
        if (!cityList.isEmpty()) {
            cityList.forEach(item -> {
                CityListVo cityListVo = new CityListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, cityListVo);
                // 获取子级城市
                cityListVo.setChildren(this.getChildCityList(item.getId()));
                cityListVoList.add(cityListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", cityListVoList);

        return JsonResult.success("操作成功", result);
    }

    /**
     * 获取子级城市
     *
     * @param pid 父级ID
     * @return
     */
    public List<CityListVo> getChildCityList(Integer pid) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        List<City> cityList = cityMapper.selectList(queryWrapper);
        List<CityListVo> cityListVoList = new ArrayList<>();
        if (!cityList.isEmpty()) {
            cityList.forEach(item -> {
                CityListVo cityListVo = new CityListVo();
                // 复制属性
                BeanUtils.copyProperties(item, cityListVo);
                // 获取子级
                List<CityListVo> childrenCityList = this.getChildCityList(item.getId());
                if (childrenCityList != null) {
                    cityListVo.setChildren(childrenCityList);
                }
                cityListVoList.add(cityListVo);
            });
        }
        return cityListVoList;
    }

    /**
     * 添加、更新城市
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(City entity) {
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
     * 删除城市
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(City entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不存在");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

}
