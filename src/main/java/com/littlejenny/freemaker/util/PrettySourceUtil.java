package com.littlejenny.freemaker.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/17
 */
public class PrettySourceUtil {
    public static List<String> getFieldNameListFromClassColumn(String classColumn) {
        return getPrettyFieldNameList(SourceUtil.getFieldNameListFromClassColumn(classColumn));
    }

    public static List<String> getFieldNameListFromClass(String classPath) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(classPath);
        return getFieldNameListFromClass(clazz);
    }

    public static List<String> getFieldNameListFromClass(Class<?> clazz) {
        return getPrettyFieldNameList(SourceUtil.getFieldNameListFromClass(clazz));
    }

    public static List<String> getFieldNameListFromExcelColumn(String excelColumn) {
        return getPrettyFieldNameList(SourceUtil.getFieldNameListFromExcelColumn(excelColumn));
    }

    private static List<String> getPrettyFieldNameList(List<String> list) {
        Integer longestLength = StringUtil.longestLength(list);
        return list.stream().map(item -> {
            return StringUtil.fillSyntaxUntil(item, longestLength, ' ');
        }).collect(Collectors.toList());
    }
}
