package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Message;
import com.javaweb.system.mapper.MessageMapper;
import com.javaweb.system.query.MessageQuery;
import com.javaweb.system.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.system.vo.message.MessageListVo;
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
 * 消息表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<MessageMapper, Message> implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        MessageQuery messageQuery = (MessageQuery) query;
        // 查询条件
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        // 消息标题
        if (!StringUtils.isEmpty(messageQuery.getTitle())) {
            queryWrapper.like("title", messageQuery.getTitle());
        }
        // 消息类型
        if (messageQuery.getType() != null && messageQuery.getType() > 0) {
            queryWrapper.eq("type", messageQuery.getType());
        }
        // 发送状态
        if (messageQuery.getSendStatus() != null && messageQuery.getSendStatus() > 0) {
            queryWrapper.eq("send_status", messageQuery.getSendStatus());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<Message> page = new Page<>(messageQuery.getPageIndex(), messageQuery.getPageSize());
        IPage<Message> data = messageMapper.selectPage(page, queryWrapper);
        List<Message> messageList = data.getRecords();
        List<MessageListVo> messageListVoList = new ArrayList<>();
        if (!messageList.isEmpty()) {
            messageList.forEach(item -> {
                MessageListVo messageListVo = new MessageListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, messageListVo);
                messageListVoList.add(messageListVo);
            });
        }
        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", messageListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 删除消息
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(Message entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不能为空");
        }
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

}
