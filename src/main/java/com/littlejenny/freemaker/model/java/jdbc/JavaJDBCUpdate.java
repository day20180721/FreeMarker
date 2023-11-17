package com.littlejenny.freemaker.model.java.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JavaJDBCUpdate {
    String tableName;
    String columnAndValueStatement;
}
