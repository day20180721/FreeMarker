private MapSqlParameterSource getInsertMapSqlParameterSource(${marker.insertedObjectName?cap_first} ${marker.insertedObjectName?uncap_first}) {
    MapSqlParameterSource map = new MapSqlParameterSource();
<#list marker.paramList as param>
    map.addValue("${param.fieldNameLowerNotCamel}", ${marker.insertedObjectName?uncap_first}.get${param.fieldNameLowerCamel?cap_first}());
</#list>
    return map;
}
