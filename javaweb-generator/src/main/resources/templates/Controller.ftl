package ${packageName}.controller;


import ${packageName}.entity.${entityName};
import ${packageName}.query.${entityName}Query;
import ${packageName}.service.I${entityName}Service;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.common.BaseController;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * ${tableAnnotation} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
@RequestMapping("/${entityName?lower_case}")
public class ${entityName}Controller extends BaseController {

    @Autowired
    private I${entityName}Service ${entityName?uncap_first}Service;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/list")
    @RequiresPermissions("${entityName?lower_case}:list")
    public JsonResult list(@RequestBody ${entityName}Query query) {
        return ${entityName?uncap_first}Service.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "${tableAnnotation}", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @RequiresPermissions("${entityName?lower_case}:add")
    public JsonResult add(@RequestBody ${entityName} entity) {
        return ${entityName?uncap_first}Service.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param id 记录ID
     * @return
     */
    @GetMapping("/info")
    @RequiresPermissions("${entityName?lower_case}:info")
    public JsonResult info(Integer id) {
        return ${entityName?uncap_first}Service.info(id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "${tableAnnotation}", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @RequiresPermissions("${entityName?lower_case}:edit")
    public JsonResult edit(@RequestBody ${entityName} entity) {
        return ${entityName?uncap_first}Service.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param entity
     * @return
     */
    @Log(title = "${tableAnnotation}", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    @RequiresPermissions("${entityName?lower_case}:drop")
    public JsonResult delete(@RequestBody ${entityName} entity) {
        return ${entityName?uncap_first}Service.delete(entity);
    }
}