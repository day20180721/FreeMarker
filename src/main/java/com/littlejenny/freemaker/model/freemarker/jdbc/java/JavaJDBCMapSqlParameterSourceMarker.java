package com.littlejenny.freemaker.model.freemarker.jdbc.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/15
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JavaJDBCMapSqlParameterSourceMarker {
    private List<JavaJDBCMapSqlParameterSource> paramList;
    private String insertedObjectName;
}
