package com.littlejenny.freemaker.service;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface InsertService {
    String insertByClassProperty(String properties, String databaseTable) throws IOException, TemplateException;

    String insertByClassPath(String classPath, String databaseTable) throws IOException, TemplateException, ClassNotFoundException;

}
