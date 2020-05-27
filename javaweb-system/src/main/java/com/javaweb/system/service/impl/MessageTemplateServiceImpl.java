package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.constant.ConfigConstant;
import com.javaweb.system.entity.MessageTemplate;
import com.javaweb.system.mapper.MessageTemplateMapper;
import com.javaweb.system.query.MessageTemplateQuery;
import com.javaweb.system.service.IMessageTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.messagetemplate.MessageTemplateListVo;
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
 * 消息模板表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class MessageTemplateServiceImpl extends BaseServiceImpl<MessageTemplateMapper, MessageTemplate> implements IMessageTemplateService {

    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        MessageTemplateQuery messageTemplateQuery = (MessageTemplateQuery) query;
        // 查询条件
        QueryWrapper<MessageTemplate> queryWrapper = new QueryWrapper<>();
        // 模板标题
        if (!StringUtils.isEmpty(messageTemplateQuery.getTitle())) {
            queryWrapper.like("title", messageTemplateQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<MessageTemplate> page = new Page<>(messageTemplateQuery.getPageIndex(), messageTemplateQuery.getPageSize());
        IPage<MessageTemplate> data = messageTemplateMapper.selectPage(page, queryWrapper);
        List<MessageTemplate> messageTemplateList = data.getRecords();
        List<MessageTemplateListVo> messageTemplateListVoList = new ArrayList<>();
        if (!messageTemplateList.isEmpty()) {
            messageTemplateList.forEach(item -> {
                MessageTemplateListVo messageTemplateListVo = new MessageTemplateListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, messageTemplateListVo);
                messageTemplateListVoList.add(messageTemplateListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", messageTemplateListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 添加、编辑消息模板
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(MessageTemplate entity) {
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
     * 删除消息模板
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(MessageTemplate entity) {
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
    public JsonResult setStatus(MessageTemplate entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }

    /**
     * 获取参数
     *
     * @return
     */
    @Override
    public JsonResult getParamList() {
        Map<String, Object> result = new HashMap<>();
        result.put("type_list", ConfigConstant.MESSAGE_TEMPLATE_TYPE_LIST);
        return JsonResult.success("操作成功", result);
    }

}
