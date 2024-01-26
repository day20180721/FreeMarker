package ${dto.packageName};
/**
* @author 王洪棟 - Lin
* @TableName ${tableClass.tableName}
* @createDate ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
<#if tableClass.importList?seq_contains("java.util.Date")>
import java.sql.Timestamp;
</#if>
<#list tableClass.importList as fieldType>
    <#if fieldType != "java.util.Date">
        ${"\n"}import ${fieldType};
    </#if>
</#list>
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${dto.fileName} implements Serializable {

<#list tableClass.allFields as field>
    /**
    * ${field.remark!}
    */<#if !field.nullable || field.jdbcType=="VARCHAR">${"\n    "}</#if><#if !field.nullable><#if field.jdbcType=="VARCHAR">@NotBlank(message="[${field.remark!}]不能為空")<#else>@NotNull(message="[${field.remark!}]不能為空")</#if></#if><#if field.jdbcType=="VARCHAR"><#if !field.nullable>${"\n    "}</#if>@Size(max= ${field.columnLength?c},message="字串長度不能超過${field.columnLength?c}")</#if><#if field.jdbcType=="VARCHAR">@Length(max= ${field.columnLength},message="字串長度不能超過${field.columnLength}")</#if>
    <#if field.shortTypeName=="Date">
    private Timestamp ${field.fieldName};
    <#else>
    private ${field.shortTypeName} ${field.fieldName};
    </#if>
</#list>
}
