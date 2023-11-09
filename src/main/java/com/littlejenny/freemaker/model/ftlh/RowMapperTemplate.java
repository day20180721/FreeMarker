package com.littlejenny.freemaker.model.ftlh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RowMapperTemplate {
    private String classPackage;
    private String classPath;
    private String className;
    private List<RowMapperField> fields;
}
