package com.littlejenny.freemaker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExcelRow {
    private String name;
    private String procReadType;
    private String procWriteType;
    private Integer procTypeLength;
    private String printFormat;

    private boolean nullable;
}
