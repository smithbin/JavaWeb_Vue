package com.javaweb.system.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.Dep;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface IDepService extends IBaseService<Dep> {

    /**
     * 获取部门列表
     *
     * @return
     */
    JsonResult getDeptList();

    /**
     * 获取部门名称
     *
     * @param deptId 部门ID
     * @return
     */
    String getDeptName(Integer deptId);

}
