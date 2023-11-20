package com.littlejenny.freemaker.controller;

import com.littlejenny.freemaker.model.java.jdbc.JavaJDBCInsert;
import com.littlejenny.freemaker.model.java.jdbc.JavaJDBCUpdate;
import com.littlejenny.freemaker.model.java.jdbc.JavaJDBCMapSqlParameterSource;
import com.littlejenny.freemaker.util.FreeMarkerUtil;
import com.littlejenny.freemaker.util.SourceUtil;
import com.littlejenny.freemaker.util.StringUtil;
import com.littlejenny.freemaker.wrapper.FieldNameListWrapper;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${target.java.jdbc}")
public class JavaJDBCController {

    @ResponseBody
    @GetMapping("${function.insert}" + "${source.java.class_column}")
    public String insertFromClassColumn(String classColumn, String tableName) throws TemplateException, IOException {
        // workDate
        List<String> fieldNameList = SourceUtil.getFieldNameListFromClassColumn(classColumn);
        return insert(fieldNameList, tableName);
    }

    @ResponseBody
    @GetMapping("${function.insert}" + "${source.java.class}")
    public String insertFromClass(String classPath, String tableName) throws TemplateException, IOException, ClassNotFoundException {
        //workDate
        List<String> fieldNameList = SourceUtil.getFieldNameListFromClass(classPath);
        return insert(fieldNameList, tableName);
    }

    @ResponseBody
    @GetMapping("${function.insert}" + "${source.excel.column}")
    public String insertFromExcelColumn(String excelColumn, String tableName) throws TemplateException, IOException {
        // WORK_DATE
        List<String> fieldNameList = SourceUtil.getFieldNameListFromExcelColumn(excelColumn);
        FieldNameListWrapper columnWrapper = new FieldNameListWrapper(fieldNameList);
        String sqlInsertColumnStatement = columnWrapper.toUpperCase().addPreAndSuffix("", ",\n").removeLengthFromLastIndex(",\n".length()).toListString();

        FieldNameListWrapper valueWrapper = new FieldNameListWrapper(fieldNameList);
        String sqlInsertValueStatement = valueWrapper.toLowerCase().addPreAndSuffix(":", ",\n").removeLengthFromLastIndex(",\n".length()).toListString();

        JavaJDBCInsert javaJDBCInsert = new JavaJDBCInsert(tableName, sqlInsertColumnStatement, sqlInsertValueStatement);
        Map<String, Object> param = FreeMarkerUtil.param().put("javaJDBCInsert", javaJDBCInsert).build();
        return FreeMarkerUtil.getResult(param, "java-jdbc-Insert");
    }

    private String insert(List<String> fieldNameList, String tableName) throws TemplateException, IOException {
        FieldNameListWrapper columnWrapper = new FieldNameListWrapper(fieldNameList);
        String sqlInsertColumnStatement = columnWrapper.toNotCamel().toUpperCase().addPreAndSuffix("", ",\n").removeLengthFromLastIndex(",\n".length()).toListString();

        FieldNameListWrapper valueWrapper = new FieldNameListWrapper(fieldNameList);
        String sqlInsertValueStatement = valueWrapper.toNotCamel().addPreAndSuffix(":", ",\n").removeLengthFromLastIndex(",\n".length()).toListString();

        JavaJDBCInsert javaJDBCInsert = new JavaJDBCInsert(tableName, sqlInsertColumnStatement, sqlInsertValueStatement);
        Map<String, Object> param = FreeMarkerUtil.param().put("javaJDBCInsert", javaJDBCInsert).build();
        return FreeMarkerUtil.getResult(param, "java-jdbc-Insert");
    }

    @ResponseBody
    @GetMapping("${function.update}" + "${source.java.class_column}")
    public String updateFromClassColumn(String classColumn, String tableName) throws TemplateException, IOException {
        // workDate
        List<String> fieldNameList = SourceUtil.getFieldNameListFromClassColumn(classColumn);
        return update(fieldNameList, tableName);
    }

    @ResponseBody
    @GetMapping("${function.update}" + "${source.java.class}")
    public String updateFromClass(String classPath, String tableName) throws TemplateException, IOException, ClassNotFoundException {
        // workDate
        List<String> fieldNameList = SourceUtil.getFieldNameListFromClass(classPath);
        return update(fieldNameList, tableName);
    }

    @ResponseBody
    @GetMapping("${function.update}" + "${source.excel.column}")
    public String updateFromExcelColumn(String excelColumn, String tableName) throws TemplateException, IOException {
        // WORK_Date
        List<String> fieldNameList = SourceUtil.getFieldNameListFromExcelColumn(excelColumn);

        FieldNameListWrapper valueWrapper = new FieldNameListWrapper(fieldNameList);
        //WORK_Date -> :work_date
        List<String> valueList = valueWrapper.toLowerCase().expand().addPreAndSuffix(":", "").build();

        FieldNameListWrapper columnWrapper = new FieldNameListWrapper(fieldNameList);
        //WORK_Date  -> WORK_DATE = :work_date -> WORK_DATE = :work_date,\n
        String columnAndValueStatement = columnWrapper
                .toUpperCase()
                .expand()
                .concat(valueList, " = ")
                .addPreAndSuffix("", ",\n")
                .removeLengthFromLastIndex(",\n".length())
                .toListString();

        JavaJDBCUpdate javaJDBCUpdate = new JavaJDBCUpdate(tableName, columnAndValueStatement);

        Map<String, Object> param = FreeMarkerUtil.param().put("javaJDBCUpdate", javaJDBCUpdate).build();

        return FreeMarkerUtil.getResult(param, "java-jdbc-update");
    }

    private String update(List<String> fieldNameList, String tableName) throws TemplateException, IOException {
        FieldNameListWrapper valueWrapper = new FieldNameListWrapper(fieldNameList);
        //work_date -> :work_date
        List<String> valueList = valueWrapper.toNotCamel().expand().addPreAndSuffix(":", "").build();

        FieldNameListWrapper columnWrapper = new FieldNameListWrapper(fieldNameList);
        //work_date -> WORK_DATE -> WORK_DATE = :work_date -> WORK_DATE = :work_date,\n
        String columnAndValueStatement = columnWrapper.
                toNotCamel()
                .toUpperCase()
                .expand()
                .concat(valueList, " = ")
                .addPreAndSuffix("", ",\n")
                .removeLengthFromLastIndex(",\n".length())
                .toListString();

        JavaJDBCUpdate javaJDBCUpdate = new JavaJDBCUpdate(tableName, columnAndValueStatement);

        Map<String, Object> param = FreeMarkerUtil.param().put("javaJDBCUpdate", javaJDBCUpdate).build();

        return FreeMarkerUtil.getResult(param, "java-jdbc-update");
    }

    @ResponseBody
    @GetMapping("${function.map_sql_param_src}" + "${source.java.class}")
    public String mapSqlParamSrcFromClass(String classPath) throws ClassNotFoundException, TemplateException, IOException {
        Class<?> clazz = Class.forName(classPath);
        List<JavaJDBCMapSqlParameterSource> itemList = getSqlParameterSourcesList(clazz);
        Map<String, Object> param = FreeMarkerUtil.param().put("itemList", itemList).put("parentName", clazz.getSimpleName()).build();
        return FreeMarkerUtil.getResult(param, "java-jdbc-MapSqlParameterSource");
    }

    private List<JavaJDBCMapSqlParameterSource> getSqlParameterSourcesList(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return Arrays.stream(fields).map(field -> {
            String camel = field.getName();
            String notCamel = StringUtil.toNotCamel(camel);
            Boolean isNullable = isFieldNullable(field);
            return new JavaJDBCMapSqlParameterSource(camel, notCamel, isNullable);
        }).collect(Collectors.toList());
    }

    private boolean isFieldNullable(Field field) {
        return field.getAnnotation(NotNull.class) == null;
    }
}
