package com.littlejenny.freemaker;


import com.littlejenny.freemaker.model.java.jdbc.JavaJDBCInsert;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

public class JavaBaseTest {
    @Test
    void splitLine(){
        String s = "   4  3  2 ";
        String[] split = s.split(" ");
        for (String string : split) {
            System.out.print(string.trim());
        }
    }
    @Test
    void getModifier(){
        for (Field field : JavaJDBCInsert.class.getDeclaredFields()) {

            System.out.println(field.getAnnotation(NotNull.class) == null);
        }
    }

    @Test
    void getPropertyName(){


    }
}
