package com.littlejenny.freemaker.controller;

import com.littlejenny.freemaker.service.ExcelInsertService;
import com.littlejenny.freemaker.service.ExcelUpdateService;
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
@RequestMapping("/jdbc/from/excel")
public class ExcelController {
    @Autowired
    ExcelInsertService excelInsertService;
    @Autowired
    ExcelUpdateService excelUpdateService;

    @ResponseBody
    @GetMapping("/properties")
    public String fromJavaPropertiesAndDatabaseTable(String properties, String databaseTable) throws TemplateException, IOException {
        String insert = excelInsertService.insertByExcel(properties, databaseTable);
        String update = excelUpdateService.updateByExcel(properties, databaseTable);
        return insert + "\n\n" + update;
    }
}
