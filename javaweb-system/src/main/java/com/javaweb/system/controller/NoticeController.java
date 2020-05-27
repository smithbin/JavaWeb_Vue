package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Notice;
import com.javaweb.system.query.NoticeQuery;
import com.javaweb.system.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 通知公告表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    @Autowired
    private INoticeService noticeService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody NoticeQuery query) {
        return noticeService.getList(query);
    }

    /**
     * 添加通知公告
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "通知公告管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody Notice entity) {
        return noticeService.edit(entity);
    }

    /**
     * 编辑通知公告
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "通知公告管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Notice entity) {
        return noticeService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 通知公告ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return noticeService.info(id);
    }

    /**
     * 删除通知公告
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "通知公告管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Notice entity) {
        return noticeService.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "通知公告管理", businessType = BusinessType.STATUS)
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody Notice entity) {
        return noticeService.setStatus(entity);
    }

    /**
     * 设置置顶
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "通知公告管理", businessType = BusinessType.STATUS)
    @PostMapping("/setIsTop")
    public JsonResult setIsTop(@RequestBody Notice entity) {
        return noticeService.setIsTop(entity);
    }

    /**
     * 获取参数
     *
     * @return
     */
    @PostMapping("/getParamList")
    public JsonResult getParamList() {
        return noticeService.getParamList();
    }

}
