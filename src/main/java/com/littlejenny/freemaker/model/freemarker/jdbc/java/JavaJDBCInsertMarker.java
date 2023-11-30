package com.littlejenny.freemaker.model.freemarker.jdbc.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JavaJDBCInsertMarker {
    private String tableName;
    private String columnStatement;
    private String valueStatement;
}
