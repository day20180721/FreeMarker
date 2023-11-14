package com.littlejenny.freemaker.controller;

import com.littlejenny.freemaker.service.InsertService;
import com.littlejenny.freemaker.service.UpdateService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/jdbc")
public class JDBCController {
    @Autowired
    InsertService insertService;
    @Autowired
    UpdateService updateService;

    @ResponseBody
    @GetMapping("/from/java/properties")
    public String fromJavaPropertiesAndDatabaseTable(String properties, String databaseTable) throws TemplateException, IOException {
        String insert = insertService.insertByClassProperty(properties, databaseTable);
        String update = updateService.updateByClassProperty(properties, databaseTable);
        return insert + "\n\n" + update;
    }

    @ResponseBody
    @GetMapping("/from/java/class")
    public String fromJavaClassAndDatabaseTable(String clazz, String databaseTable) throws TemplateException, IOException, ClassNotFoundException {
        String insert = insertService.insertByClassPath(clazz, databaseTable);
        String update = updateService.updateByClassPath(clazz, databaseTable);
        return insert + "\n\n" + update;
    }
}
