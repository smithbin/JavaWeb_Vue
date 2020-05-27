package com.javaweb.generator.service;

import com.javaweb.common.common.IBaseService;
import com.javaweb.generator.entity.GenTableColumn;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface IGenTableColumnService extends IBaseService<GenTableColumn> {

    /**
     * 查询表字段信息
     *
     * @param tableId 表ID
     * @return
     */
    List<GenTableColumn> selectGenTableColumnListByTableId(Integer tableId);

}
