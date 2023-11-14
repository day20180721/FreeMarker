package com.littlejenny.freemaker.service.impl;

import com.littlejenny.freemaker.model.Update;
import com.littlejenny.freemaker.service.UpdateService;
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
import java.util.List;

@Service
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    Configuration freeMarkerConfiguration;

    @Override
    public String updateByClassProperty(String properties, String databaseTable) throws IOException, TemplateException {
        List<String> propertyList = getPropertyListFromRawString(properties);
        return update(propertyList, databaseTable);
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
    public String updateByClassPath(String classPath, String databaseTable) throws IOException, TemplateException, ClassNotFoundException {
        Class<?> clazz = Class.forName(classPath);
        List<String> propertyListFromClass = getPropertyListFromClass(clazz);
        return update(propertyListFromClass, databaseTable);
    }

    private List<String> getPropertyListFromClass(Class<?> clazz) {
        List<String> propertyList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            propertyList.add(field.getName());
        }
        return propertyList;
    }

    private String update(List<String> propertyList, String databaseTable) throws IOException, TemplateException {
        Template template = freeMarkerConfiguration.getTemplate("update.ftlh");

        String columnListString = getColumnListString(propertyList);

        Update update = new Update(columnListString, databaseTable);

        String result = "";

        try (StringWriter stringWriter = new StringWriter()) {
            template.process(update, stringWriter);
            result = stringWriter.toString();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    private String getColumnListString(List<String> propertyList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : propertyList) {
            String underLineLowerCase = StringUtil.toUnderLineLowerCase(s);
            String colon = ":" + s;
            String row = underLineLowerCase + " = " + colon + ",\n";
            stringBuilder.append(row);
        }
        int lastIndex = stringBuilder.lastIndexOf(",");
        return stringBuilder.substring(0, lastIndex);
    }
}
