package com.littlejenny.freemaker.service;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface ExcelInsertService {
    String insertByExcel(String properties, String databaseTable) throws IOException, TemplateException;

}
