package com.javaweb.generator.service.impl;

import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.generator.entity.GenTableColumn;
import com.javaweb.generator.mapper.GenTableColumnMapper;
import com.javaweb.generator.service.IGenTableColumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class GenTableColumnServiceImpl extends BaseServiceImpl<GenTableColumnMapper, GenTableColumn> implements IGenTableColumnService {

    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    /**
     * 获取表字段信息
     *
     * @param tableId 表ID
     * @return
     */
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(Integer tableId) {
        return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
    }

}
