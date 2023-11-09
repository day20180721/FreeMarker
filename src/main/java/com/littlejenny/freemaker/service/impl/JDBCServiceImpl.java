package com.littlejenny.freemaker.service.impl;

import com.littlejenny.freemaker.model.ftlh.RowMapperField;
import com.littlejenny.freemaker.service.JDBCService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JDBCServiceImpl implements JDBCService {
    @Autowired
    Configuration freeMarkerConfiguration;

    @Override
    public String fromPropertiesAndDatabaseTable(String properties, String databaseTable) throws IOException, TemplateException {
        Template template = freeMarkerConfiguration.getTemplate("rowMapper.ftlh");

        return "";
    }
    private List<RowMapperField> getRowMapperFieldListFromProperties(String properties){
        List<RowMapperField> list = new ArrayList<>();

        return list;
    }
}
