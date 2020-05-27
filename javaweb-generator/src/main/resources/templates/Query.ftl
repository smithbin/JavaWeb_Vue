package ${packageName}.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * ${tableAnnotation}查询条件
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
public class ${entityName}Query extends BaseQuery {

<#if model_column?exists>
    <#list model_column as model>
    <#if model.columnName = 'name'>
    /**
     * ${model.columnComment!}
     */
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnName = 'title'>
    /**
     * ${model.columnComment!}
     */
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnName = 'mobile'>
    /**
     * ${model.columnComment!}
     */
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnName = 'type'>
    /**
     * ${model.columnComment!}
     */
    private Integer ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnName = 'status'>
    /**
     * ${model.columnComment!}
     */
    private Integer ${model.changeColumnName?uncap_first};

    </#if>
    </#list>
</#if>
}
