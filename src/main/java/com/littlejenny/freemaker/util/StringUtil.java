package com.littlejenny.freemaker.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StringUtil {

    public static List<String> toNotCamel(List<String> stringList) {
        return stringList.stream().map(StringUtil::toNotCamel).collect(Collectors.toList());
    }

    public static String toNotCamel(String string) {
        Set<Character> allUpperCase = getUpperCaseSet(string);
        return convertUpperCaseToUnderLineLowerCase(string, allUpperCase.toArray(new Character[]{}));
    }

    private static Set<Character> getUpperCaseSet(String string) {
        char[] charArray = string.toCharArray();
        Set<Character> upperCaseSet = new HashSet<>();
        for (char c : charArray) {
            if (Character.isUpperCase(c)) {
                upperCaseSet.add(c);
            }
        }
        return upperCaseSet;
    }

    private static String convertUpperCaseToUnderLineLowerCase(String string, Character[] allUpperCaseLetter) {
        String result = string;
        for (Character c : allUpperCaseLetter) {
            char lowerCase = Character.toLowerCase(c);
            result = result.replaceAll(c + "", "_" + lowerCase);
        }
        return result;
    }

    public static List<String> toCamel(List<String> stringList) {
        return stringList.stream().map(StringUtil::toCamel).collect(Collectors.toList());
    }

    public static String toCamel(String string) {
        Set<String> needToReplaceSet = getUnderLineLowerCaseSet(string);
        return convertUnderLineLowerCaseToUpperCase(string, needToReplaceSet.toArray(new String[]{}));
    }

    private static Set<String> getUnderLineLowerCaseSet(String string) {
        Set<String> underLineLowerCaseSet = new HashSet<>();

        List<Integer> indexList = getSyntaxIndexListInString(string, '_');

        for (int i = indexList.size() - 1; i >= 0; i--) {
            int index = indexList.get(i);
            //adb_d
            //abc'here is index '_d -> _d
            String underLineLowerCase = string.substring(index, index + 2);
            underLineLowerCaseSet.add(underLineLowerCase);
        }
        return underLineLowerCaseSet;
    }

    private static String convertUnderLineLowerCaseToUpperCase(String string, String[] needToReplaceArray) {
        String result = string;
        for (String needToReplace : needToReplaceArray) {
            //_d -> D
            String replacement = needToReplace.substring(1, 2).toUpperCase();
            result = result.replaceAll(needToReplace, replacement);
        }
        return result;
    }

    private static List<Integer> getSyntaxIndexListInString(String s, char syntax) {
        List<Integer> indexList = new ArrayList<>();
        for (int index = s.indexOf(syntax);
             index >= 0;
             index = s.indexOf(syntax, index + 1)) {
            indexList.add(index);
        }
        return indexList;
    }

    public static List<String> toUpperCase(List<String> stringList) {
        return stringList.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    public static List<String> toLowerCase(List<String> stringList) {
        return stringList.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    public static List<String> addPreAndSuffix(List<String> stringList, String prefix, String suffix) {
        return stringList.stream().map(item -> {
            return prefix + item + suffix;
        }).collect(Collectors.toList());
    }
    public static String toString(List<String> stringList){
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : stringList) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
    public static String removeLastWithLength(String string,Integer suffix){
        return string.substring(0,string.length() - suffix);
    }
    public static List<String> concat(List<String> source,List<String> asPart, String concator) {
        if (source.size() != asPart.size()) throw new RuntimeException("兩個List長度不相同");
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < source.size(); i++) {
            newList.add(source.get(i) + concator + asPart.get(i));
        }
        return newList;
    }
    public static Integer longestLength(List<String> list){
        Integer longest = 0;
        for (String string : list) {
            if(string.length() > longest) longest = string.length();
        }
        return longest;
    }
    public static String fillSyntaxUntil(String string,Integer length,Character syntax){
        StringBuilder stringBuilder = new StringBuilder(string);
        int count = length - string.length();
        for (int i = 0; i < count; i++) {
            stringBuilder.append(syntax);
        }
        return stringBuilder.toString();
    }
}
