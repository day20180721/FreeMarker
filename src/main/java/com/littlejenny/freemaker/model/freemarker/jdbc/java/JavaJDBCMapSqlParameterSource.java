package com.littlejenny.freemaker.model.freemarker.jdbc.java;

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
    private String fieldNameLowerCamel;
    private String fieldNameLowerNotCamel;
}
