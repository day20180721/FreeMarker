package com.littlejenny.freemaker.service;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface ExcelUpdateService {
    String updateByExcel(String properties, String databaseTable) throws IOException, TemplateException;

}
