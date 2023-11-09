package com.littlejenny.freemaker.model.ftlh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RowMapperField {
    private String propertyName;
    private String resultSetFieldType;
}
