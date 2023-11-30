package com.littlejenny.freemaker.wrapper;

import com.littlejenny.freemaker.model.ExcelRow;
import com.littlejenny.freemaker.model.freemarker.proc.ProcGrWholeFileMarker;
import com.littlejenny.freemaker.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/20
 */

public class ProcGrExcelRowListWrapper {
    private final List<ExcelRow> excelRowList;
    private final Map<String, List<ExcelRow>> typePropertyNameReadMap = new HashMap<>();
    private final Map<String, List<ExcelRow>> typePropertyNameWriteMap = new HashMap<>();

    private ProcGrExcelRowListWrapper(List<ExcelRow> excelRowList) {
        this.excelRowList = excelRowList;

        for (ExcelRow excelRow : this.excelRowList) {
            addToExistOneOrCreateReadList(excelRow);
            addToExistOneOrCreateWriteList(excelRow);
        }
    }

    private void addToExistOneOrCreateReadList(ExcelRow excelRow) {
        String procReadType = excelRow.getProcReadType();
        addToExistOneOrCreateList(excelRow, procReadType, typePropertyNameReadMap);
    }

    private void addToExistOneOrCreateWriteList(ExcelRow excelRow) {
        String procWriteType = excelRow.getProcWriteType();
        addToExistOneOrCreateList(excelRow, procWriteType, typePropertyNameWriteMap);
    }

    private void addToExistOneOrCreateList(ExcelRow excelRow, String procWriteType, Map<String, List<ExcelRow>> typePropertyNameWriteMap) {
        if (typePropertyNameWriteMap.containsKey(procWriteType)) {
            List<ExcelRow> propertyList = typePropertyNameWriteMap.get(procWriteType);
            propertyList.add(excelRow);
        } else {
            List<ExcelRow> propertyList = new ArrayList<>();
            propertyList.add(excelRow);
            typePropertyNameWriteMap.put(procWriteType, propertyList);
        }
    }

    public static ProcGrExcelRowListWrapper of(List<ExcelRow> excelRowList) {
        ArrayList<ExcelRow> copy = new ArrayList<>(excelRowList);
        return new ProcGrExcelRowListWrapper(copy);
    }

    private final int expandLength = 3;

    public String getReadProperty() {
        StringBuilder stringBuilder = new StringBuilder();

        Integer longestLength = StringUtil.longestLength(new ArrayList<>(typePropertyNameReadMap.keySet()));

        for (String procType : typePropertyNameReadMap.keySet()) {
            stringBuilder.append(procType).append("\n");

            for (ExcelRow excelRow : typePropertyNameReadMap.get(procType)) {
                String expandedProperty = StringUtil.fillBeforeSyntaxUntil("r_" + excelRow.getName(), longestLength + expandLength, ' ');
                stringBuilder.append(expandedProperty);
                stringBuilder.append("[FETCH_SIZE]");
                stringBuilder.append("[").append(excelRow.getProcTypeLength()).append("]");
                stringBuilder.append(",\n");
            }
            // 當成一個資料型態的結尾，後續用來更換為";"
            stringBuilder.append("\t");
        }
        return stringBuilder.toString().replace(",\n\t", ";\n");
    }

    public String getWriteProperty() {
        StringBuilder stringBuilder = new StringBuilder();

        Integer longestLength = StringUtil.longestLength(new ArrayList<>(typePropertyNameWriteMap.keySet()));

        for (String procType : typePropertyNameWriteMap.keySet()) {
            stringBuilder.append(procType).append("\n");

            for (ExcelRow excelRow : typePropertyNameWriteMap.get(procType)) {
                String expandedProperty = StringUtil.fillBeforeSyntaxUntil(excelRow.getName(), longestLength + expandLength, ' ');
                stringBuilder.append(expandedProperty);
                stringBuilder.append("[").append(excelRow.getProcTypeLength()).append("]");
                stringBuilder.append(",\n");
            }
            // 當成一個資料型態的結尾，後續用來更換為";"
            stringBuilder.append("\t");
        }
        return stringBuilder.toString().replace(",\n\t", ";\n");
    }

    public String getCheckForNullProperty() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("short\n");
        List<String> excelRowNameList = excelRowList.stream().filter(ExcelRow::isNullable).map(ExcelRow::getName).collect(Collectors.toList());

        for (String excelRowName : excelRowNameList) {
            stringBuilder.append("l_");
            stringBuilder.append(excelRowName);
            stringBuilder.append("[FETCH_SIZE]");
            stringBuilder.append(",\n");
        }
        return StringUtil.removeLastWithLength(stringBuilder.toString(),",\n".length());
    }

    public String getSqlFetchStatement() {
        List<String> nameList = getExcelRowName();
        FieldNameListWrapper fieldNameListWrapper = FieldNameListWrapper.of(nameList).expand().addPreAndSuffix(":r_", ",\n").removeLengthFromLastIndex(",\n".length());
        return fieldNameListWrapper.toListString();
    }

    public String getCopyValue() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ExcelRow excelRow : excelRowList) {
            String setNull = "set_null(&r_" + excelRow.getName() + "[in]);\n";
            System.out.println(setNull);
            String strcpy = "strcpy(" + excelRow.getName() + "," + "(char *)r_" + excelRow.getName() + "[in].arr);\n";
            stringBuilder.append(setNull);
            stringBuilder.append(strcpy);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getPropertyNameList() {
        List<String> nameList = getExcelRowName();
        FieldNameListWrapper fieldNameListWrapper = FieldNameListWrapper.of(nameList).expand().addPreAndSuffix("", ",\n").removeLengthFromLastIndex(",\n".length());
        return fieldNameListWrapper.toListString();
    }


    public String getPrintFormat() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ExcelRow excelRow : excelRowList) {
            stringBuilder.append(excelRow.getPrintFormat());
        }
        return stringBuilder.toString();
    }

    public ProcGrWholeFileMarker getWholeFile(String tableName) {
        ProcGrWholeFileMarker procGrWholeFileMarker = new ProcGrWholeFileMarker();
        procGrWholeFileMarker.setTableNameUpperButNotCamel(tableName.toUpperCase());
        procGrWholeFileMarker.setTableNameLowerButNotCamel(tableName.toLowerCase());
        procGrWholeFileMarker.setCopyValue(getCopyValue());
        procGrWholeFileMarker.setReadProperty(getReadProperty());
        procGrWholeFileMarker.setCheckForNullProperty(getCheckForNullProperty());

        procGrWholeFileMarker.setWriteProperty(getWriteProperty());
        procGrWholeFileMarker.setPrettyPropertyNameList(getPropertyNameList());
        procGrWholeFileMarker.setSqlFetchStatement(getSqlFetchStatement());
        procGrWholeFileMarker.setPrintFormat(getPrintFormat());
        procGrWholeFileMarker.setFilterForErrorMsg("");

        return procGrWholeFileMarker;
    }

    private List<String> getExcelRowName() {
        return excelRowList.stream().map(ExcelRow::getName).collect(Collectors.toList());
    }
}
