package com.littlejenny.freemaker.service.impl;

import com.littlejenny.freemaker.model.Insert;
import com.littlejenny.freemaker.service.InsertService;
import com.littlejenny.freemaker.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InsertServiceImpl implements InsertService {
    @Autowired
    Configuration freeMarkerConfiguration;

    @Override
    public String insertByClassProperty(String properties, String databaseTable) throws IOException, TemplateException {
        List<String> propertyList = getPropertyListFromRawString(properties);
        return insert(propertyList, databaseTable);
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

    @Override
    public String insertByClassPath(String classPath, String databaseTable) throws IOException, TemplateException, ClassNotFoundException {
        Class<?> clazz = Class.forName(classPath);
        List<String> propertyListFromClass = getPropertyListFromClass(clazz);
        return insert(propertyListFromClass, databaseTable);
    }

    private List<String> getPropertyListFromClass(Class<?> clazz) {
        List<String> propertyList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            propertyList.add(field.getName());
        }
        return propertyList;
    }

    private String insert(List<String> propertyList, String databaseTable) throws IOException, TemplateException {
        Template template = freeMarkerConfiguration.getTemplate("insert.ftlh");

        List<String> propertyListWithColon = getPropertyListWithColon(propertyList);

        List<String> underLineLowerCasePropertyList = getUnderLineLowerCasePropertyList(propertyList);

        String propertyListString = getPropertyListString(underLineLowerCasePropertyList);
        String propertyListWithColonString = getPropertyListWithColonString(propertyListWithColon);

        Insert insert = new Insert(propertyListString, propertyListWithColonString, databaseTable);

        String result = "";

        try (StringWriter stringWriter = new StringWriter()) {
            template.process(insert, stringWriter);
            result = stringWriter.toString();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    private List<String> getPropertyListWithColon(List<String> propertyList) {
        return propertyList.stream().map(item -> {
            return ":" + item;
        }).collect(Collectors.toList());
    }

    private List<String> getUnderLineLowerCasePropertyList(List<String> propertyList) {
        List<String> list = new ArrayList<>();
        for (String string : propertyList) {
            list.add(StringUtil.toUnderLineLowerCase(string));
        }
        return list;
    }

    private String getPropertyListString(List<String> propertyList) {
        String result = propertyList.toString();
        return result.substring(1, result.length() - 1);
    }

    private String getPropertyListWithColonString(List<String> propertyListWithColon) {
        String result = propertyListWithColon.toString();
        return result.substring(1, result.length() - 1);
    }
}
