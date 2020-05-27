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
import com.javaweb.system.entity.Item;
import com.javaweb.system.entity.Layout;
import com.javaweb.system.entity.LayoutDesc;
import com.javaweb.system.mapper.ItemMapper;
import com.javaweb.system.mapper.LayoutMapper;
import com.javaweb.system.query.LayoutQuery;
import com.javaweb.system.service.ILayoutDescService;
import com.javaweb.system.service.ILayoutService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.layout.LayoutInfoVo;
import com.javaweb.system.vo.layout.LayoutListVo;
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
 * 页面布局管理 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class LayoutServiceImpl extends BaseServiceImpl<LayoutMapper, Layout> implements ILayoutService {

    @Autowired
    private LayoutMapper layoutMapper;

    @Autowired
    private ILayoutDescService layoutDescService;

    @Autowired
    private ItemMapper itemMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        LayoutQuery layoutQuery = (LayoutQuery) query;
        // 查询条件
        QueryWrapper<Layout> queryWrapper = new QueryWrapper<>();
        // 站点ID
        if (layoutQuery.getItemId() != null && layoutQuery.getItemId() > 0) {
            queryWrapper.eq("item_id", layoutQuery.getItemId());
        }
        // 位置
        if (layoutQuery.getLocId() != null && layoutQuery.getLocId() > 0) {
            queryWrapper.eq("loc_id", layoutQuery.getLocId());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        IPage<Layout> page = new Page<>(layoutQuery.getPageIndex(), layoutQuery.getPageSize());
        IPage<Layout> data = layoutMapper.selectPage(page, queryWrapper);
        List<Layout> layoutList = data.getRecords();
        List<LayoutListVo> layoutListVoList = new ArrayList<>();
        if (!layoutList.isEmpty()) {
            layoutList.forEach(item -> {
                LayoutListVo layoutListVo = new LayoutListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, layoutListVo);
                // 图片
                layoutListVo.setImage(CommonUtils.getImageURL(layoutListVo.getImage()));
                // 获取站点名称
                Item itemInfo = itemMapper.selectById(item.getItemId());
                if (itemInfo != null) {
                    layoutListVo.setItemName(itemInfo.getName());
                }
                // 推荐类型
                if (item.getType() > 0) {
                    layoutListVo.setTypeName(ConfigConstant.LAYOUT_TYPE_LIST.get(item.getType()));
                }
                // 获取布局描述信息
                LayoutDesc layoutDescInfo = layoutDescService.getLayoutDescInfo(item.getItemId(), item.getLocId());
                layoutListVo.setLocName(layoutDescInfo.getLocDesc() + "=>" + layoutDescInfo.getLocId());
                layoutListVoList.add(layoutListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", layoutListVoList);
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
        Layout entity = (Layout) super.getInfo(id);
        // 图片
        if (!StringUtils.isEmpty(entity.getImage())) {
            entity.setImage(CommonUtils.getImageURL(entity.getImage()));
        }
        // 拷贝属性
        LayoutInfoVo layoutInfoVo = new LayoutInfoVo();
        BeanUtils.copyProperties(entity, layoutInfoVo);
        return layoutInfoVo;
    }

    /**
     * 添加、编辑推荐
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Layout entity) {
        // 图片处理
        if (!StringUtils.isEmpty(entity.getImage()) && entity.getImage().contains(CommonConfig.imageURL)) {
            entity.setImage(entity.getImage().replaceAll(CommonConfig.imageURL, ""));
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
     * 删除推荐
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(Layout entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不存在");
        }
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 获取基础参数
     *
     * @return
     */
    @Override
    public JsonResult getParamList() {
        Map<String, Object> result = new HashMap<>();
        result.put("type_list", ConfigConstant.LAYOUT_TYPE_LIST);
        return JsonResult.success("操作成功", result);
    }

}
