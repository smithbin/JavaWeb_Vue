package com.javaweb.system.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Dep;
import com.javaweb.system.query.DepQuery;
import com.javaweb.system.service.IDepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@RestController
@RequestMapping("/dep")
public class DepController extends BaseController {

    @Autowired
    private IDepService depService;

    /**
     * 获取数据列表
     *
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody DepQuery query) {
        return depService.getList(query);
    }

    /**
     * 添加部门
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public JsonResult add(@RequestBody Dep entity) {
        return depService.edit(entity);
    }

    /**
     * 编辑部门
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Dep entity) {
        return depService.edit(entity);
    }

    /**
     * 获取部门详情
     *
     * @param id 部门ID
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(Integer id) {
        return depService.info(id);
    }

    /**
     * 删除部门
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Dep entity) {
        return depService.delete(entity);
    }

    /**
     * 获取部门列表
     *
     * @return
     */
    @PostMapping("/getDeptList")
    public JsonResult getDeptList() {
        return depService.getDeptList();
    }

}
