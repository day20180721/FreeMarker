package com.littlejenny.freemaker.service.impl;

import com.littlejenny.freemaker.model.Insert;
import com.littlejenny.freemaker.service.ExcelInsertService;
import com.littlejenny.freemaker.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExcelInsertServiceImpl implements ExcelInsertService {
    @Autowired
    Configuration freeMarkerConfiguration;

    @Override
    public String insertByExcel(String properties, String databaseTable) throws IOException, TemplateException {

        Template template = freeMarkerConfiguration.getTemplate("insert.ftlh");
        //WORK_DATE
        List<String> propertyList = getPropertyListFromRawString(properties);
        //WORK_DATE -> work_date
        List<String> underLineLowerCasePropertyList = getUnderLineLowerCasePropertyList(propertyList);
        //work_date -> workDate
        List<String> colonPropertyList = getColonPropertyList(underLineLowerCasePropertyList);
        //work_date
        String underLineLowerCaseString = getUnderLineLowerCaseString(underLineLowerCasePropertyList);
        //:workDate
        String colonString = getColonString(colonPropertyList);

        Insert insert = new Insert(underLineLowerCaseString, colonString, databaseTable);

        String result = "";

        try (StringWriter stringWriter = new StringWriter()) {
            template.process(insert, stringWriter);
            result = stringWriter.toString();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    private List<String> getPropertyListFromRawString(String properties) {
        List<String> list = new ArrayList<>();
        String[] splitLine = properties.split("\n");
        for (String s : splitLine) {
            if (s.isEmpty()) continue;
            String[] splitColumn = s.split(" ");

            int lastIndex = splitColumn.length - 1;
            String propertyName = splitColumn[lastIndex];

            propertyName = propertyName.replace(";", "");

            list.add(propertyName);
        }
        return list;
    }

    private List<String> getColonPropertyList(List<String> propertyList) {
        return propertyList.stream().map(item -> {
            return ":" + StringUtil.toUpperCase(item);
        }).collect(Collectors.toList());
    }

    private List<String> getUnderLineLowerCasePropertyList(List<String> propertyList) {
        List<String> list = new ArrayList<>();
        for (String string : propertyList) {
            list.add(string.toLowerCase());
        }
        return list;
    }

    private String getUnderLineLowerCaseString(List<String> propertyList) {
        String result = propertyList.toString();
        return result.substring(1, result.length() - 1);
    }

    private String getColonString(List<String> propertyListWithColon) {
        String result = propertyListWithColon.toString();
        return result.substring(1, result.length() - 1);
    }
}
