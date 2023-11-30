package com.littlejenny.freemaker.model.freemarker.jdbc.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JavaJDBCUpdateMarker {
    String tableName;
    String columnAndValueStatement;
}
