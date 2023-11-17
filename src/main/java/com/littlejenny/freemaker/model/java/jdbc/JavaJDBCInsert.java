package com.littlejenny.freemaker.model.java.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JavaJDBCInsert {
    private String tableName;
    private String columnStatement;
    private String valueStatement;

}
