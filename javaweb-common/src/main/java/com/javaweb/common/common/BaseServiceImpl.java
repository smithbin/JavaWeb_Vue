package com.javaweb.common.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.common.utils.JsonResult;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    /**
     * 根据查询条件获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        return null;
    }

    /**
     * 根据ID获取记录信息
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public JsonResult info(Integer id) {
        if (id == null && id <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        Object result = this.getInfo(id);
        return JsonResult.success("操作成功", result);
    }

    /**
     * 根据ID获取记录信息
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        T entity = this.getById(id);
        return entity;
    }

    /**
     * 传入实体对象添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult add(T entity) {
//        return JsonResult.error("演示系统禁止操作");
        boolean result = this.save(entity);
        if (!result) {
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    /**
     * 传入实体对象更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult update(T entity) {
//        return JsonResult.error("演示系统禁止操作");
        boolean result = this.updateById(entity);
        if (!result) {
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    /**
     * 根据实体对象添加、编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(T entity) {
//        return JsonResult.error("演示系统禁止操作");
        boolean result = this.saveOrUpdate(entity);
        if (!result) {
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(T entity) {
//        return JsonResult.error("演示系统禁止操作");
        boolean result = this.updateById(entity);
        if (!result) {
            return JsonResult.error();
        }
        return JsonResult.success("删除成功");
    }

    /**
     * 根据ID删除记录
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public JsonResult deleteById(Integer id) {
//        return JsonResult.error("演示系统禁止操作");
        if (StringUtils.isEmpty(id)) {
            return JsonResult.error("记录ID不能为空");
        }
        boolean result = this.removeById(id);
        if (!result) {
            return JsonResult.error();
        }
        return JsonResult.success("删除成功");
    }

    /**
     * 根据ID删除记录
     *
     * @param ids 记录ID
     * @return
     */
    @Override
    public JsonResult deleteByIds(String ids) {
//        return JsonResult.error("演示系统禁止操作");
        if (StringUtils.isEmpty(ids)) {
            return JsonResult.error("记录ID不能为空");
        }
        String[] item = ids.split(",");
        Integer totalNum = 0;
        for (String id : item) {
            boolean result = this.removeById(id);
            if (result) {
                totalNum++;
            }
        }
        return JsonResult.error(String.format("本次共删除【%s】条记录", totalNum));
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult setStatus(T entity) {
        return this.update(entity);
    }

    /**
     * 获取基础参数
     *
     * @return
     */
    @Override
    public JsonResult getParamList() {
        return null;
    }

    /**
     * 导出Excel
     *
     * @return
     */
    @Override
    public List<T> exportExcel() {
        return null;
    }
}
