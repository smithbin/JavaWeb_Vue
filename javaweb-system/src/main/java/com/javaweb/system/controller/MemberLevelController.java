package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Member;
import com.javaweb.system.entity.MemberLevel;
import com.javaweb.system.query.MemberLevelQuery;
import com.javaweb.system.service.IMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 会员级别表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/memberlevel")
public class MemberLevelController extends BaseController {

    @Autowired
    private IMemberLevelService memberLevelService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody MemberLevelQuery query) {
        return memberLevelService.getList(query);
    }

    /**
     * 新增数据
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "会员等级", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody MemberLevel entity) {
        return memberLevelService.edit(entity);
    }

    /**
     * 编辑数据
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "会员等级", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody MemberLevel entity) {
        return memberLevelService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 记录ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return memberLevelService.info(id);
    }

    /**
     * 删除数据
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "会员等级", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody MemberLevel entity) {
        return memberLevelService.delete(entity);
    }

}
