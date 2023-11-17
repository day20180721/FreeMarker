package com.littlejenny.freemaker.model.java.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/15
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JavaJDBCMapSqlParameterSource {
    private String fieldNameCamel;
    private String fieldNameNotCamel;
    private Boolean nullable;
}
