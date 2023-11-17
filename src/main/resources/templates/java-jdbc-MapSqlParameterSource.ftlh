private MapSqlParameterSource getInsertMapSqlParameterSource(${parentName} ${parentName?uncap_first}) {
    MapSqlParameterSource map = new MapSqlParameterSource();
<#list itemList as item>
    <#if item.nullable>
        if (${parentName?uncap_first}.get${item.fieldNameCamel?cap_first}() != null){
            map.addValue("${item.fieldNameNotCamel}", ${parentName?uncap_first}.get${item.fieldNameCamel?cap_first}());
        }
    <#else>
            map.addValue("${item.fieldNameNotCamel}", ${parentName?uncap_first}.get${item.fieldNameCamel?cap_first}());
    </#if>
</#list>
    return map;
}
