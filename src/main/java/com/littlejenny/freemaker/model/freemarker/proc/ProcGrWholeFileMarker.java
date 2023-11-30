package com.littlejenny.freemaker.model.freemarker.proc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcGrWholeFileMarker {
    private String printFormat;

    private String tableNameUpperButNotCamel;
    private String tableNameLowerButNotCamel;
    private String writeProperty;
    private String readProperty;
    private String checkForNullProperty;
    private String sqlFetchStatement;
    private String copyValue;

    private String filterForErrorMsg;

    private String prettyPropertyNameList;

}
