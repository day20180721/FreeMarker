package com.littlejenny.freemaker.controller;

import com.littlejenny.freemaker.model.freemarker.jdbc.java.JavaJDBCInsertMarker;
import com.littlejenny.freemaker.model.freemarker.jdbc.java.JavaJDBCMapSqlParameterSource;
import com.littlejenny.freemaker.model.freemarker.jdbc.java.JavaJDBCUpdateMarker;
import com.littlejenny.freemaker.model.freemarker.jdbc.java.JavaJDBCMapSqlParameterSourceMarker;
import com.littlejenny.freemaker.util.FreeMarkerUtil;
import com.littlejenny.freemaker.util.SourceUtil;
import com.littlejenny.freemaker.wrapper.FieldNameListWrapper;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${target.java.jdbc}")
public class JavaJDBCController {
    @ResponseBody
    @GetMapping("${function.insert}" + "${from.java.class.column}")
    public String insertFromClassColumn(String classColumn, String tableName) throws TemplateException, IOException {
        // workDate
        List<String> fieldNameList = SourceUtil.getFieldNameListFromClassColumn(classColumn);
        return insert(fieldNameList, tableName);
    }

    @ResponseBody
    @GetMapping("${function.insert}" + "${from.java.class}")
    public String insertFromClass(String classPath, String tableName) throws TemplateException, IOException, ClassNotFoundException {
        //workDate
        List<String> fieldNameList = SourceUtil.getFieldNameListFromClass(classPath);
        return insert(fieldNameList, tableName);
    }

    @ResponseBody
    @GetMapping("${function.insert}" + "${from.excel.column}")
    public String insertFromExcelColumn(String excelColumn, String tableName) throws TemplateException, IOException {
        // WORK_DATE
        List<String> fieldNameList = SourceUtil.getFieldNameListFromExcelColumn(excelColumn);
        FieldNameListWrapper columnWrapper = FieldNameListWrapper.of(fieldNameList);
        String sqlInsertColumnStatement = columnWrapper.toUpperCase().addPreAndSuffix("", ",\n").removeLengthFromLastIndex(",\n".length()).toListString();

        FieldNameListWrapper valueWrapper = FieldNameListWrapper.of(fieldNameList);
        String sqlInsertValueStatement = valueWrapper.toLowerCase().addPreAndSuffix(":", ",\n").removeLengthFromLastIndex(",\n".length()).toListString();

        JavaJDBCInsertMarker marker = new JavaJDBCInsertMarker(tableName, sqlInsertColumnStatement, sqlInsertValueStatement);
        Map<String, Object> param = FreeMarkerUtil.param().put("marker", marker).build();
        return FreeMarkerUtil.getResult(param, "java-jdbc-Insert");
    }

    private String insert(List<String> fieldNameList, String tableName) throws TemplateException, IOException {
        FieldNameListWrapper columnWrapper = FieldNameListWrapper.of(fieldNameList);
        String sqlInsertColumnStatement = columnWrapper.toNotCamel().toUpperCase().addPreAndSuffix("", ",\n").removeLengthFromLastIndex(",\n".length()).toListString();

        FieldNameListWrapper valueWrapper = FieldNameListWrapper.of(fieldNameList);
        String sqlInsertValueStatement = valueWrapper.toNotCamel().addPreAndSuffix(":", ",\n").removeLengthFromLastIndex(",\n".length()).toListString();

        JavaJDBCInsertMarker marker = new JavaJDBCInsertMarker(tableName, sqlInsertColumnStatement, sqlInsertValueStatement);
        Map<String, Object> param = FreeMarkerUtil.param().put("marker", marker).build();
        return FreeMarkerUtil.getResult(param, "java-jdbc-Insert");
    }

    @ResponseBody
    @GetMapping("${function.update}" + "${from.java.class.column}")
    public String updateFromClassColumn(String classColumn, String tableName) throws TemplateException, IOException {
        // workDate
        List<String> fieldNameList = SourceUtil.getFieldNameListFromClassColumn(classColumn);
        return update(fieldNameList, tableName);
    }

    @ResponseBody
    @GetMapping("${function.update}" + "${from.java.class}")
    public String updateFromClass(String classPath, String tableName) throws TemplateException, IOException, ClassNotFoundException {
        // workDate
        List<String> fieldNameList = SourceUtil.getFieldNameListFromClass(classPath);
        return update(fieldNameList, tableName);
    }

    @ResponseBody
    @GetMapping("${function.update}" + "${from.excel.column}")
    public String updateFromExcelColumn(String excelColumn, String tableName) throws TemplateException, IOException {
        // WORK_Date
        List<String> fieldNameList = SourceUtil.getFieldNameListFromExcelColumn(excelColumn);

        FieldNameListWrapper valueWrapper = FieldNameListWrapper.of(fieldNameList);
        //WORK_Date -> :work_date
        List<String> valueList = valueWrapper.toLowerCase().expand().addPreAndSuffix(":", "").build();

        FieldNameListWrapper columnWrapper = FieldNameListWrapper.of(fieldNameList);
        //WORK_Date  -> WORK_DATE = :work_date -> WORK_DATE = :work_date,\n
        String columnAndValueStatement = columnWrapper
                .toUpperCase()
                .expand()
                .concat(valueList, " = ")
                .addPreAndSuffix("", ",\n")
                .removeLengthFromLastIndex(",\n".length())
                .toListString();

        JavaJDBCUpdateMarker marker = new JavaJDBCUpdateMarker(tableName, columnAndValueStatement);

        Map<String, Object> param = FreeMarkerUtil.param().put("marker", marker).build();

        return FreeMarkerUtil.getResult(param, "java-jdbc-update");
    }

    private String update(List<String> fieldNameList, String tableName) throws TemplateException, IOException {
        FieldNameListWrapper valueWrapper = FieldNameListWrapper.of(fieldNameList);
        //work_date -> :work_date
        List<String> valueList = valueWrapper.toNotCamel().expand().addPreAndSuffix(":", "").build();

        FieldNameListWrapper columnWrapper = FieldNameListWrapper.of(fieldNameList);
        //work_date -> WORK_DATE -> WORK_DATE = :work_date -> WORK_DATE = :work_date,\n
        String columnAndValueStatement = columnWrapper.
                toNotCamel()
                .toUpperCase()
                .expand()
                .concat(valueList, " = ")
                .addPreAndSuffix("", ",\n")
                .removeLengthFromLastIndex(",\n".length())
                .toListString();

        JavaJDBCUpdateMarker marker = new JavaJDBCUpdateMarker(tableName, columnAndValueStatement);

        Map<String, Object> param = FreeMarkerUtil.param().put("marker", marker).build();

        return FreeMarkerUtil.getResult(param, "java-jdbc-update");
    }

    @ResponseBody
    @GetMapping("${function.map_sql_param_src}" + "${from.java.class}")
    public String mapSqlParamSrcFromClass(String classPath) throws ClassNotFoundException, TemplateException, IOException {
        Class<?> clazz = Class.forName(classPath);
        List<String> fieldNameList = SourceUtil.getFieldNameListFromClass(classPath);
        List<JavaJDBCMapSqlParameterSource> paramList = getSqlParameterSourcesListFromClass(fieldNameList);
        JavaJDBCMapSqlParameterSourceMarker marker = new JavaJDBCMapSqlParameterSourceMarker(paramList, clazz.getSimpleName());
        Map<String, Object> param = FreeMarkerUtil.param().put("marker", marker).build();
        return FreeMarkerUtil.getResult(param, "java-jdbc-MapSqlParameterSource");
    }

    @ResponseBody
    @GetMapping("${function.map_sql_param_src}" + "${from.java.class.column}")
    public String mapSqlParamSrcFromClassColumn(String classColumn, String tableName) throws ClassNotFoundException, TemplateException, IOException {
        List<String> fieldNameList = SourceUtil.getFieldNameListFromClassColumn(classColumn);
        List<JavaJDBCMapSqlParameterSource> paramList = getSqlParameterSourcesListFromClass(fieldNameList);
        JavaJDBCMapSqlParameterSourceMarker marker = new JavaJDBCMapSqlParameterSourceMarker(paramList, tableName);
        Map<String, Object> param = FreeMarkerUtil.param().put("marker", marker).build();
        return FreeMarkerUtil.getResult(param, "java-jdbc-MapSqlParameterSource");
    }

    @ResponseBody
    @GetMapping("${function.map_sql_param_src}" + "${from.excel.column}")
    public String mapSqlParamSrcFromExcelColumn(String excelColumn, String tableName) throws ClassNotFoundException, TemplateException, IOException {
        List<String> fieldNameList = SourceUtil.getFieldNameListFromExcelColumn(excelColumn);
        List<JavaJDBCMapSqlParameterSource> paramList = getSqlParameterSourcesListFromExcelColumn(fieldNameList);
        JavaJDBCMapSqlParameterSourceMarker marker = new JavaJDBCMapSqlParameterSourceMarker(paramList, tableName);
        Map<String, Object> param = FreeMarkerUtil.param().put("marker", marker).build();
        return FreeMarkerUtil.getResult(param, "java-jdbc-MapSqlParameterSource");
    }

    private List<JavaJDBCMapSqlParameterSource> getSqlParameterSourcesListFromClass(List<String> fieldNameList) {
        List<JavaJDBCMapSqlParameterSource> paramList = new ArrayList<>();
        FieldNameListWrapper fieldNameListWrapper = FieldNameListWrapper.of(fieldNameList);
        List<String> notCamel = fieldNameListWrapper.toNotCamel().build();
        int fieldLength = fieldNameList.size();
        for (int i = 0; i < fieldLength; i++) {
            JavaJDBCMapSqlParameterSource param = new JavaJDBCMapSqlParameterSource(fieldNameList.get(i), notCamel.get(i));
            paramList.add(param);
        }
        return paramList;
    }

    private List<JavaJDBCMapSqlParameterSource> getSqlParameterSourcesListFromExcelColumn(List<String> fieldNameList) {
        List<JavaJDBCMapSqlParameterSource> paramList = new ArrayList<>();
        FieldNameListWrapper camelWrapper = FieldNameListWrapper.of(fieldNameList);
        List<String> camel = camelWrapper.toLowerCase().toCamel().build();
        FieldNameListWrapper notCamelWrapper = FieldNameListWrapper.of(fieldNameList);
        List<String> notCamel = notCamelWrapper.toLowerCase().build();
        int fieldLength = fieldNameList.size();
        for (int i = 0; i < fieldLength; i++) {
            JavaJDBCMapSqlParameterSource param = new JavaJDBCMapSqlParameterSource(camel.get(i), notCamel.get(i));
            paramList.add(param);
        }
        return paramList;
    }
}
