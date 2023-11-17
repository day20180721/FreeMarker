package com.littlejenny.freemaker.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/17
 */
public class SourceUtil {
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
     * private Timestamp workDate; -> workDate
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

    public static List<String> getFieldNameListFromExcelColumn(String excelColumn) {
        List<String> list = new ArrayList<>();
        String[] lineArray = excelColumn.split("\n");
        for (String line : lineArray) {
            if (line.isEmpty()) continue;
            list.add(line.trim());
        }
        return list;
    }
}
