package com.littlejenny.freemaker.controller;

import com.littlejenny.freemaker.service.JavaInsertService;
import com.littlejenny.freemaker.service.JavaUpdateService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/jdbc/from/java")
public class JavaController {
    @Autowired
    JavaInsertService javaInsertService;
    @Autowired
    JavaUpdateService javaUpdateService;

    @ResponseBody
    @GetMapping("/properties")
    public String fromJavaPropertiesAndDatabaseTable(String properties, String databaseTable) throws TemplateException, IOException {
        String insert = javaInsertService.insertByClassProperty(properties, databaseTable);
        String update = javaUpdateService.updateByClassProperty(properties, databaseTable);
        return insert + "\n\n" + update;
    }

    @ResponseBody
    @GetMapping("/class")
    public String fromJavaClassAndDatabaseTable(String clazz, String databaseTable) throws TemplateException, IOException, ClassNotFoundException {
        String insert = javaInsertService.insertByClassPath(clazz, databaseTable);
        String update = javaUpdateService.updateByClassPath(clazz, databaseTable);
        return insert + "\n\n" + update;
    }
}
