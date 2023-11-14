package com.littlejenny.freemaker.service;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface JavaUpdateService {
    String updateByClassProperty(String properties, String databaseTable) throws IOException, TemplateException;
    String updateByClassPath(String classPath, String databaseTable) throws IOException, TemplateException, ClassNotFoundException;
}
