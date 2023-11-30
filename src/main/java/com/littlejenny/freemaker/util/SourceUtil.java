package com.littlejenny.freemaker.util;

import com.littlejenny.freemaker.model.ExcelRow;
import com.littlejenny.freemaker.model.handler.ProcGrOracleTypeHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/17
 */
public class SourceUtil {
    /*
        private Date workDate;
        private Integer dataId;
    */
    public static List<String> getFieldNameListFromClassColumn(String classColumn) {
        List<String> list = new ArrayList<>();
        String[] lineArray = classColumn.split("\n");
        for (String line : lineArray) {
            if (!isFieldLine(line)) continue;
            list.add(getFieldName(line));
        }

        return list;
    }

    /**
     * private Timestamp workDate; -> use last index ->workDate
     */
    private static String getFieldName(String line) {
        String[] splitColumn = line.split(" ");

        int lastIndex = splitColumn.length - 1;
        String propertyName = splitColumn[lastIndex];

        return propertyName.replace(";", "");
    }

    /**
     * Not start with / * @
     */
    private static boolean isFieldLine(String line) {
        if (line.trim().isEmpty()) return false;
        return Character.isLetter(line.trim().charAt(0));
    }

    /*
        com.littlejenny.freemaker.model.RtPxPromItems
     */
    public static List<String> getFieldNameListFromClass(String classPath) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(classPath);
        return getFieldNameListFromClass(clazz);
    }

    public static List<String> getFieldNameListFromClass(Class<?> clazz) {
        List<String> list = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            list.add(field.getName());
        }
        return list;
    }

    /*
        WORK_DATE
        DATA_ID
    */
    public static List<String> getFieldNameListFromExcelColumn(String excelColumn) {
        List<String> list = new ArrayList<>();
        String[] lineArray = excelColumn.split("\n");
        for (String line : lineArray) {
            if (line.isEmpty()) continue;
            list.add(line.trim().toUpperCase());
        }
        return list;
    }

    /*
        WORK_DATE	DATE
        DATA_ID	NUMBER(10)
    */
    private static final ProcGrOracleTypeHandler PROC_GR_ORACLE_TYPE_CHAIN = new ProcGrOracleTypeHandler();

    public static List<ExcelRow> getExcelRowFromOracle(String excelColumn) {
        String[] lineArray = excelColumn.toUpperCase().split("\n");
        return Arrays.stream(lineArray).map(SourceUtil::getExcelRowFromLine).collect(Collectors.toList());
    }

    private static ExcelRow getExcelRowFromLine(String line) {
        line = line.trim();
        String[] propertyArray = line.split("\t");
        String propertyName = propertyArray[0].toLowerCase();
        String readType = PROC_GR_ORACLE_TYPE_CHAIN.getReadType(propertyArray[1]);
        String writeType = PROC_GR_ORACLE_TYPE_CHAIN.getWriteType(propertyArray[1]);
        String writeFormatSyntax = PROC_GR_ORACLE_TYPE_CHAIN.getWriteFormatSyntax(propertyArray[1]);
        int length = PROC_GR_ORACLE_TYPE_CHAIN.length(propertyArray[1]);

        Boolean nullable = propertyArray.length == 2;

        return new ExcelRow(propertyName, readType, writeType, length, writeFormatSyntax, nullable);
    }
}
