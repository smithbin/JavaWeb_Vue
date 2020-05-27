package com.javaweb.generator.controller;


import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.ConvertUtil;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.generator.entity.GenTable;
import com.javaweb.generator.entity.GenTableColumn;
import com.javaweb.generator.query.GenTableQuery;
import com.javaweb.generator.service.IGenTableColumnService;
import com.javaweb.generator.service.IGenTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成业务表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-25
 */
@RestController
@RequestMapping("/gentable")
public class GenTableController extends BaseController {

    @Autowired
    private IGenTableService genTableService;

    @Autowired
    private IGenTableColumnService genTableColumnService;

    /**
     * 获取业务表列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(@RequestBody GenTableQuery query) {
        return genTableService.getList(query);
    }

    /**
     * 获取数据库表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/genDbTableList")
    public JsonResult genDbTableList(@RequestBody GenTableQuery query) {
        List<GenTable> genTableList = genTableService.genDbTableList(query);
        return JsonResult.success("操作成功", genTableList);
    }

    /**
     * 导入表
     *
     * @param tables 数据表
     * @return
     */
    @Log(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public JsonResult importTable(@RequestBody String tables) {
        String[] tableNames = ConvertUtil.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return JsonResult.success();
    }

    /**
     * 获取表详情信息
     *
     * @param tableId 表ID
     * @return
     */
    @GetMapping("/getTableInfo")
    public JsonResult getTableInfo(String tableId) {
        GenTable table = genTableService.selectGenTableById(Integer.valueOf(tableId));
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(Integer.valueOf(tableId));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("records", list);
        return JsonResult.success("操作成功", map);
    }

    /**
     * 更新代码生成表信息
     *
     * @param genTable 生成表
     * @return
     */
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PostMapping("/updateGenTable")
    public JsonResult updateGenTable(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return JsonResult.success();
    }

    /**
     * 删除业务表
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody GenTable entity) {
        return genTableService.delete(entity);
    }

    /**
     * 生成代码
     *
     * @param tables
     * @throws Exception
     */
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @PostMapping("/batchGenCode")
    public JsonResult batchGenCode(@RequestBody String tables) throws IOException {
        String[] tableNames = ConvertUtil.toStrArray(tables);
        return genTableService.generatorCode(tableNames);
    }

}
