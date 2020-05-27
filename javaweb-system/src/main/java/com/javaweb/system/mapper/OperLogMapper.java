package com.javaweb.system.mapper;

import com.javaweb.system.entity.OperLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface OperLogMapper extends BaseMapper<OperLog> {

    /**
     * 创建系统操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperlog(OperLog operLog);

}
