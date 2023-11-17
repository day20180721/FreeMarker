package com.littlejenny.freemaker.service;

import com.littlejenny.freemaker.service.impl.JavaInsertServiceImpl;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/15
 */
@SpringBootTest
public class JavaJavaJDBCInsertServiceImplTest {
    @Autowired
    JavaInsertServiceImpl javaInsertService;
    @Test
    void mapSqlParameterSourceByClassPath() throws TemplateException, IOException, ClassNotFoundException {
        System.out.println(javaInsertService.mapSqlParameterSourceByClassPath("com.littlejenny.freemaker.model.RtPxPromItems"));
    }
}
