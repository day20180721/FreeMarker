package com.littlejenny.freemaker.service.impl;

import com.littlejenny.freemaker.model.Update;
import com.littlejenny.freemaker.service.ExcelUpdateService;
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
public class ExcelUpdateServiceImpl implements ExcelUpdateService {
    @Autowired
    Configuration freeMarkerConfiguration;

    @Override
    public String updateByExcel(String properties, String databaseTable) throws IOException, TemplateException {
        Template template = freeMarkerConfiguration.getTemplate("update.ftlh");
        //WORK_DATE
        List<String> propertyList = getPropertyListFromRawString(properties);
        //WORK_DATE -> work_date
        List<String> underLineLowerCasePropertyList = getUnderLineLowerCasePropertyList(propertyList);
        //work_date -> workDate
        List<String> colonPropertyList = getColonPropertyList(underLineLowerCasePropertyList);
        //work_date = :workDate
        String rowString = getRowString(colonPropertyList, underLineLowerCasePropertyList);

        Update update = new Update(rowString, databaseTable);

        String result = "";

        try (StringWriter stringWriter = new StringWriter()) {
            template.process(update, stringWriter);
            result = stringWriter.toString();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    private String getRowString(List<String> colonPropertyList, List<String> underLineLowerCasePropertyList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (colonPropertyList.size() != underLineLowerCasePropertyList.size()) {
            throw new RuntimeException("colonPropertyList 數量不等於 underLineLowerCasePropertyList");
        }
        for (int i = 0; i < colonPropertyList.size(); i++) {
            String colonProperty = colonPropertyList.get(i);
            String underLineLower = underLineLowerCasePropertyList.get(i);
            String row = underLineLower + " = " + colonProperty + ",\n";
            stringBuilder.append(row);
        }
        int lastIndex = stringBuilder.lastIndexOf(",");
        return stringBuilder.substring(0, lastIndex);
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

    private String getPropertyListString(List<String> propertyList) {
        String result = propertyList.toString();
        return result.substring(1, result.length() - 1);
    }

    private String getPropertyListWithColonString(List<String> propertyListWithColon) {
        String result = propertyListWithColon.toString();
        return result.substring(1, result.length() - 1);
    }
}
