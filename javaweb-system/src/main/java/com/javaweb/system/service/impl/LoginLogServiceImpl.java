package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.common.BaseServiceImpl;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.LoginLog;
import com.javaweb.system.mapper.LoginLogMapper;
import com.javaweb.system.query.LoginLogQuery;
import com.javaweb.system.service.ILoginLogService;
import com.javaweb.system.vo.loginlog.LoginLogListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录日志表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        LoginLogQuery loginLogQuery = (LoginLogQuery) query;
        // 查询条件
        QueryWrapper<LoginLog> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(loginLogQuery.getLoginName())) {
            queryWrapper.like("login_name", loginLogQuery.getLoginName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<LoginLog> page = new Page<>(loginLogQuery.getPageIndex(), loginLogQuery.getPageSize());
        IPage<LoginLog> data = loginLogMapper.selectPage(page, queryWrapper);
        List<LoginLog> loginLogList = data.getRecords();
        List<LoginLogListVo> loginLogListVoList = new ArrayList<>();
        if (!loginLogList.isEmpty()) {
            loginLogList.forEach(item -> {
                LoginLogListVo loginLogListVo = new LoginLogListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, loginLogListVo);
                loginLogListVoList.add(loginLogListVo);
            });
        }
        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", loginLogListVoList);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 删除登录日志
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(LoginLog entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            return JsonResult.error("记录ID不存在");
        }
        entity.setMark(0);
        return super.delete(entity);
    }

    /**
     * 创建系统登录日志
     *
     * @param loginLog 访问日志对象
     */
    @Override
    public void insertLoginLog(LoginLog loginLog) {
        loginLogMapper.insertLoginLog(loginLog);
    }
}
