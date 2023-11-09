package com.littlejenny.freemaker.service;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface JDBCService {
    String fromPropertiesAndDatabaseTable(String properties, String databaseTable) throws IOException, TemplateException;
}
