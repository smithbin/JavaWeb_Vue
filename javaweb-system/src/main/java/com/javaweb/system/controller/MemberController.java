package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Member;
import com.javaweb.system.query.MemberQuery;
import com.javaweb.system.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/member")
public class MemberController extends BaseController {

    @Autowired
    private IMemberService memberService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody MemberQuery query) {
        return memberService.getList(query);
    }

    /**
     * 添加用户
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "会员管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody Member entity) {
        return memberService.edit(entity);
    }

    /**
     * 编辑用户
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "会员管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Member entity) {
        return memberService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 用户ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return memberService.info(id);
    }

    /**
     * 删除用户
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "会员管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Member entity) {
        return memberService.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody Member entity) {
        return memberService.setStatus(entity);
    }

    /**
     * 获取设备类型
     *
     * @return
     */
    @PostMapping("/getParamList")
    public JsonResult getParamList() {
        return memberService.getParamList();
    }

}
