package ${daoImpl.packageName};

/**
* @author 王洪棟 - Lin
* @description 針對表【${tableClass.tableName}<#if tableClass.remark?has_content>(${tableClass.remark!})</#if>】的數據庫操作Dao
* @createDate ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ${daoInterface.packageName}.${daoInterface.fileName};
import ${dto.packageName}.${dto.fileName};

import java.util.stream.Collectors;
import java.util.List;

@Repository
@Slf4j(topic = ".info")
public class ${daoImpl.fileName} implements ${daoInterface.fileName}   {
    @Autowired
    @Qualifier("jdbcTemplate")
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<${dto.fileName}> select(){
        List<${dto.fileName}> list = jdbcTemplate.query(selectSql(), new BeanPropertyRowMapper<>(${dto.fileName}.class));
        log.info("取得 " + list.size() + " 筆 ${dto.fileName}");
        return list;
    }
    private String selectSql(){
        return "select * from ${tableClass.tableName}";
    }

    @Override
    public int insertBatch(List<${dto.fileName}> ${dto.fileName?uncap_first}List){
        MapSqlParameterSource[] mapArray = getInsertBatchMapSqlParameterSourceList(${dto.fileName?uncap_first}List);
        int[] result = jdbcTemplate.batchUpdate(insertBatchSql(), mapArray);
        log.info("新增 " + result.length + " 筆 ${dto.fileName}");
        return result.length;
    }
    private MapSqlParameterSource[] getInsertBatchMapSqlParameterSourceList(List<${dto.fileName}> ${dto.fileName?uncap_first}List) {
        return ${dto.fileName?uncap_first}List.stream().map(this::getInsertBatchMapSqlParameterSource).collect(Collectors.toList()).toArray(new MapSqlParameterSource[0]);
    }
    private MapSqlParameterSource getInsertBatchMapSqlParameterSource(${dto.fileName} ${dto.fileName?uncap_first}) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        return map;
    }

    private String insertBatchSql(){
        return "insert into ${tableClass.tableName} () values()";
    }

    @Override
    public int updateBatch(List<${dto.fileName}> ${dto.fileName?uncap_first}List){
        MapSqlParameterSource[] mapArray = getUpdateMapSqlParameterSourceList(${dto.fileName?uncap_first}List);
        int[] result = jdbcTemplate.batchUpdate(updateBatchSql(), mapArray);
        log.info("更新 " + result.length + " 筆 ${dto.fileName}");
        return result.length;
    }
    private MapSqlParameterSource[] getUpdateMapSqlParameterSourceList(List<${dto.fileName}> ${dto.fileName?uncap_first}List) {
        return ${dto.fileName?uncap_first}List.stream().map(this::getUpdateMapSqlParameterSource).collect(Collectors.toList()).toArray(new MapSqlParameterSource[0]);
    }
    private MapSqlParameterSource getUpdateMapSqlParameterSource(${dto.fileName} ${dto.fileName?uncap_first}) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        return map;
    }
    private String updateBatchSql(){
        return "update ${tableClass.tableName} set where";
    }

    @Override
    public int delete(){
        int deleteCount = jdbcTemplate.update(deleteSql(), new MapSqlParameterSource());
        log.info("刪除 " + deleteCount + " 筆 ${dto.fileName}");
        return deleteCount;
    }
    private String deleteSql(){
        return "delete ${tableClass.tableName} where ";
    }

    @Override
    public int deleteBatch(){
        int[] deleteCount = jdbcTemplate.batchUpdate(deleteBatchSql(), new MapSqlParameterSource[0]);
        log.info("刪除 " + deleteCount + " 筆 ${dto.fileName}");
        return deleteCount.length;
    }
    private String deleteBatchSql(){
        return "delete ${tableClass.tableName} where";
    }
}
