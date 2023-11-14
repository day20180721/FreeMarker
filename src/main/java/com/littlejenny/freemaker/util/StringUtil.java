package com.littlejenny.freemaker.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtil {
    public static String toUnderLineLowerCase(String string) {
        Character[] allUpperCase = getAllUpperCaseLetter(string);
        return convertUpperCaseToUnderLineLowerCase(string, allUpperCase);
    }

    private static Character[] getAllUpperCaseLetter(String string) {
        char[] charArray = string.toCharArray();
        Set<Character> upperCaseSet = new HashSet<>();
        for (char c : charArray) {
            if (Character.isUpperCase(c)) {
                upperCaseSet.add(c);
            }
        }
        return upperCaseSet.toArray(new Character[0]);
    }

    private static String convertUpperCaseToUnderLineLowerCase(String string, Character[] allUpperCaseLetter) {
        String result = string;
        for (Character c : allUpperCaseLetter) {
            char lowerCase = Character.toLowerCase(c);
            result = string.replaceAll(c + "", "_" + lowerCase);
        }
        return result;
    }

    public static String toUpperCase(String string) {
        Set<String> needToReplaceSet = getUnderLineLowerCaseSet(string);
        return convertUnderLineLowerCaseToUpperCase(string, needToReplaceSet);
    }

    private static List<Integer> getAllUnderLineIndex(String s) {
        List<Integer> indexList = new ArrayList<>();
        char underLine = '_';
        for (int index = s.indexOf(underLine);
             index >= 0;
             index = s.indexOf(underLine, index + 1)) {
            indexList.add(index);
        }
        return indexList;
    }

    private static Set<String> getUnderLineLowerCaseSet(String string) {
        Set<String> needToReplaceSet = new HashSet<>();
        List<Integer> indexList = getAllUnderLineIndex(string);
        for (int i = indexList.size() - 1; i >= 0; i--) {
            int index = indexList.get(i);
            //abc_d -> _d
            String needToReplace = string.substring(index, index + 2);
            needToReplaceSet.add(needToReplace);
        }
        return needToReplaceSet;
    }

    private static String convertUnderLineLowerCaseToUpperCase(String string, Set<String> needToReplaceSet) {
        String result = string;
        for (String needToReplace : needToReplaceSet) {
            //_d -> D
            String replacement = needToReplace.substring(1, 2).toUpperCase();
            result = result.replaceAll(needToReplace, replacement);
        }
        return result;
    }
}
