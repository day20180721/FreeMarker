package com.littlejenny.freemaker;


import org.junit.jupiter.api.Test;

public class JavaBaseTest {
    @Test
    void splitLine(){
        String s = "   4  3  2 ";
        String[] split = s.split(" ");
        for (String string : split) {
            System.out.print(string.trim());
        }
    }
}
