package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.constant.ConfigConstant;
import com.javaweb.system.entity.Notice;
import com.javaweb.system.mapper.NoticeMapper;
import com.javaweb.system.query.NoticeQuery;
import com.javaweb.system.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.notice.NoticeListVo;
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
 * 通知公告表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        NoticeQuery noticeQuery = (NoticeQuery) query;
        // 查询条件
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        // 通知标题
        if (!StringUtils.isEmpty(noticeQuery.getTitle())) {
            queryWrapper.like("title", noticeQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<Notice> page = new Page<>(noticeQuery.getPageIndex(), noticeQuery.getPageSize());
        IPage<Notice> data = noticeMapper.selectPage(page, queryWrapper);
        List<Notice> noticeList = data.getRecords();
        List<NoticeListVo> noticeListVoList = new ArrayList<>();
        if (!noticeList.isEmpty()) {
            noticeList.forEach(item -> {
                NoticeListVo noticeListVo = new NoticeListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, noticeListVo);
                // 来源
                noticeListVo.setSourceName(ConfigConstant.NOTICE_SOURCE_LIST.get(noticeListVo.getSource()));
                noticeListVoList.add(noticeListVo);
            });
        }
        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", noticeListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 添加、编辑通知公告
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Notice entity) {
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
     * 删除通知公告
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(Notice entity) {
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
    public JsonResult setStatus(Notice entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }

    /**
     * 设置置顶
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult setIsTop(Notice entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getIsTop() == null) {
            return JsonResult.error("是否置顶不能为空");
        }
        return this.update(entity);
    }

    /**
     * 获取参数
     *
     * @return
     */
    @Override
    public JsonResult getParamList() {
        Map<String, Object> result = new HashMap<>();
        result.put("source_list", ConfigConstant.NOTICE_SOURCE_LIST);
        return JsonResult.success("操作成功", result);
    }

}
