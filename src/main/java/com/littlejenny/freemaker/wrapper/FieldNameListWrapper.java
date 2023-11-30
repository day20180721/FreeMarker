package com.littlejenny.freemaker.wrapper;

import com.littlejenny.freemaker.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/17
 */
public class FieldNameListWrapper {
    private List<String> fieldNameList;

    private FieldNameListWrapper(List<String> fieldNameList) {
        this.fieldNameList = fieldNameList;
    }

    public static FieldNameListWrapper of(List<String> fieldNameList) {
        ArrayList<String> copy = new ArrayList<>(fieldNameList);
        return new FieldNameListWrapper(copy);
    }

    public FieldNameListWrapper toNotCamel() {
        fieldNameList = StringUtil.toNotCamel(fieldNameList);
        return this;
    }

    public FieldNameListWrapper toCamel() {
        fieldNameList = StringUtil.toCamel(fieldNameList);
        return this;
    }

    public FieldNameListWrapper toUpperCase() {
        fieldNameList = StringUtil.toUpperCase(fieldNameList);
        return this;
    }

    public FieldNameListWrapper toLowerCase() {
        fieldNameList = StringUtil.toLowerCase(fieldNameList);
        return this;
    }

    public FieldNameListWrapper addPreAndSuffix(String prefix, String suffix) {
        fieldNameList = StringUtil.addPreAndSuffix(fieldNameList, prefix, suffix);
        return this;
    }

    public FieldNameListWrapper removeLengthFromLastIndex(Integer length) {
        int lastIndex = fieldNameList.size() - 1;
        String string = fieldNameList.get(lastIndex);
        fieldNameList.remove(lastIndex);
        string = string.substring(0, string.length() - length);
        fieldNameList.add(string);
        return this;
    }

    public String toListString() {
        return StringUtil.toString(fieldNameList);
    }

    public List<String> build() {
        return new ArrayList<>(fieldNameList);
    }

    public FieldNameListWrapper concat(List<String> list, String concator) {
        fieldNameList = StringUtil.concat(fieldNameList, list, concator);
        return this;
    }

    public FieldNameListWrapper expand() {
        Integer longestLength = StringUtil.longestLength(fieldNameList);
        fieldNameList = fieldNameList.stream().map(item -> {
            return StringUtil.fillAfterSyntaxUntil(item, longestLength, ' ');
        }).collect(Collectors.toList());
        return this;
    }

    public FieldNameListWrapper expandBefore(Integer length) {
        StringBuilder expanded = new StringBuilder();
        for (Integer i = 0; i < length; i++) {
            expanded.append(' ');
        }
        String expandedString = expanded.toString();

        fieldNameList = fieldNameList.stream().map(item -> {
            return expandedString + item;
        }).collect(Collectors.toList());
        return this;
    }
}
