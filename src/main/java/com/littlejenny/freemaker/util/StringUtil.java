package com.littlejenny.freemaker.util;

import java.util.HashSet;
import java.util.Set;

public class StringUtil {
    public static String toUnderLineLowerCase(String string) {
        String result = "";
        char[] charArray = string.toCharArray();
        Character[] allUpperCaseLetter = getAllUpperCaseLetter(charArray);
        result = convertUpperCaseToUnderLineLowerCase(string, allUpperCaseLetter);

        return result;
    }

    private static Character[] getAllUpperCaseLetter(char[] charArray) {
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
}
