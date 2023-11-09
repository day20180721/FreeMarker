package com.littlejenny.freemaker.controller;

import com.littlejenny.freemaker.service.JDBCService;
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
    JDBCService jdbcService;

    @ResponseBody
    @GetMapping("/from/properties")
    public String fromPropertiesAndDatabaseTable(String properties,String databaseTable) throws TemplateException, IOException {

        return jdbcService.fromPropertiesAndDatabaseTable(properties,databaseTable);
    }
}
