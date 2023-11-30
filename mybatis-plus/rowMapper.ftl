package ${rowMapper.packageName};

/**
* @author 王洪棟 - Lin
* @TableName ${tableClass.tableName}
* @createDate ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import ${dto.packageName}.${dto.fileName};
<#list tableClass.importList as fieldType>${"\n"}import ${fieldType};</#list>
import java.sql.ResultSet;
import java.sql.SQLException;


public class ${rowMapper.fileName} implements RowMapper<${dto.fileName}> {
    @Override
    public ${dto.fileName} mapRow(ResultSet rs, int rowNum) throws SQLException {
    ${dto.fileName} ${dto.fileName?uncap_first} = new ${dto.fileName}();
<#list tableClass.allFields as field>
    <#if field.shortTypeName == "Integer">
        ${dto.fileName?uncap_first}.set${field.fieldName?cap_first}(rs.getInt("${field.fieldName}"));
    <#elseif field.shortTypeName == "Date">
        ${dto.fileName?uncap_first}.set${field.fieldName?cap_first}(rs.getTimestamp("${field.fieldName}"));
    <#else>
        ${dto.fileName?uncap_first}.set${field.fieldName?cap_first}(rs.get${field.shortTypeName}("${field.fieldName}"));
    </#if>
</#list>
        return ${dto.fileName?uncap_first};
    }
}
