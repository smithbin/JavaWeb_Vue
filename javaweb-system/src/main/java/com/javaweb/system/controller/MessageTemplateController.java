package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.MessageTemplate;
import com.javaweb.system.query.MessageTemplateQuery;
import com.javaweb.system.service.IMessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 消息模板表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/messagetemplate")
public class MessageTemplateController extends BaseController {

    @Autowired
    private IMessageTemplateService messageTemplateService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody MessageTemplateQuery query) {
        return messageTemplateService.getList(query);
    }

    /**
     * 新增消息模板
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "消息模板管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody MessageTemplate entity) {
        return messageTemplateService.edit(entity);
    }

    /**
     * 编辑消息模板
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "消息模板管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody MessageTemplate entity) {
        return messageTemplateService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 消息模板ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return messageTemplateService.info(id);
    }

    /**
     * 删除消息模板
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "消息模板管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody MessageTemplate entity) {
        return messageTemplateService.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody MessageTemplate entity) {
        return messageTemplateService.setStatus(entity);
    }

    /**
     * 获取参数列表
     *
     * @return
     */
    @PostMapping("/getParamList")
    public JsonResult getParamList() {
        return messageTemplateService.getParamList();
    }

}
