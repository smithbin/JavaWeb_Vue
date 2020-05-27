package com.javaweb.generator.mapper;

import com.javaweb.generator.entity.GenTableColumn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface GenTableColumnMapper extends BaseMapper<GenTableColumn> {

    /**
     * 根据表名查询列信息
     *
     * @param tableName 数据表名
     * @return
     */
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);

    /**
     * 插入数据表列
     *
     * @param genTableColumn 数据表列
     * @return
     */
    int insertGenTableColumn(GenTableColumn genTableColumn);

    /**
     * 获取表字段列表
     *
     * @param tableId 表ID
     * @return
     */
    List<GenTableColumn> selectGenTableColumnListByTableId(Integer tableId);

    /**
     * 修改业务表字段
     *
     * @param genTableColumn 表字段
     * @return
     */
    int updateGenTableColumn(GenTableColumn genTableColumn);

}
