package com.littlejenny.freemaker;


import com.littlejenny.freemaker.model.ExcelRow;
import com.littlejenny.freemaker.model.proc.handler.oracle.OracleToProcGrTypeHandler;
import com.littlejenny.freemaker.model.freemarker.jdbc.java.JavaJDBCInsertMarker;
import com.littlejenny.freemaker.util.DateUtil;
import com.littlejenny.freemaker.util.SourceUtil;
import com.littlejenny.freemaker.wrapper.ProcGrExcelRowListWrapper;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JavaBaseTest {
    @Test
    void splitLine() {
        String s = "   4  3  2 ";
        String[] split = s.split(" ");
        for (String string : split) {
            System.out.print(string.trim());
        }
    }

    @Test
    void getModifier() {
        for (Field field : JavaJDBCInsertMarker.class.getDeclaredFields()) {

            System.out.println(field.getAnnotation(NotNull.class) == null);
        }
    }

    @Test
    void typeChain() {
        String success = "date";
        String fail = "CC1";
        OracleToProcGrTypeHandler oracleToProcGrTypeHandler = new OracleToProcGrTypeHandler();

        System.out.println(oracleToProcGrTypeHandler.getReadType(success));

        System.out.println(oracleToProcGrTypeHandler.getReadType(fail));
    }

    @Test
    void getExcelRowFromOracle() {
        String excelColumn = "PROMOTION_NO\tNUMBER(4,0)\n" +
                "PROM_TYPE\tVARCHAR2(1)\n" +
                "PROM_LEVEL\tNUMBER(1,0)\n" +
                "BONUS_POINT\tNUMBER(5,0)\n" +
                "BLOCK\tNUMBER(1,0)\n" +
                "LIMIT_QTY\tNUMBER(3,0)\n" +
                "PROM_TXT\tVARCHAR2(30)\n" +
                "MEMO\tVARCHAR2(30)";
        System.out.println(SourceUtil.getExcelRowFromOracle(excelColumn));
    }

    @Test
    void split() {
        String a = "a";
        System.out.println(a.split(",").length);
    }

    @Test
    void getReadProperty() {
        String excelColumn = "PROMOTION_NO\tNUMBER(4,0)\n" +
                "PROM_TYPE\tVARCHAR2(1)\n" +
                "PROM_LEVEL\tNUMBER(1,0)\n" +
                "BONUS_POINT\tNUMBER(5,0)\n" +
                "BLOCK\tNUMBER(1,0)\n" +
                "LIMIT_QTY\tNUMBER(3,0)\n" +
                "PROM_TXT\tVARCHAR2(30)\n" +
                "MEMO\tVARCHAR2(30)";
        List<ExcelRow> excelRowFromOracle =
                SourceUtil.getExcelRowFromOracle(excelColumn);
        ProcGrExcelRowListWrapper procGrExcelRowListWrapper = ProcGrExcelRowListWrapper.of(excelRowFromOracle);
        System.out.println(procGrExcelRowListWrapper.getReadProperty());
    }

    @Test
    void getWriteProperty() {
        String excelColumn = "PROMOTION_NO\tNUMBER(4,0)\n" +
                "PROM_TYPE\tVARCHAR2(1)\n" +
                "PROM_LEVEL\tNUMBER(1,0)\n" +
                "BONUS_POINT\tNUMBER(5,0)\n" +
                "BLOCK\tNUMBER(1,0)\n" +
                "LIMIT_QTY\tNUMBER(3,0)\n" +
                "PROM_TXT\tVARCHAR2(30)\n" +
                "MEMO\tVARCHAR2(30)";
        List<ExcelRow> excelRowFromOracle =
                SourceUtil.getExcelRowFromOracle(excelColumn);
        ProcGrExcelRowListWrapper procGrExcelRowListWrapper = ProcGrExcelRowListWrapper.of(excelRowFromOracle);
        System.out.println(procGrExcelRowListWrapper.getWriteProperty());
    }

    @Test
    void getSqlFetchStatement() {
        String excelColumn = "PROMOTION_NO\tNUMBER(4,0)\n" +
                "PROM_TYPE\tVARCHAR2(1)\n" +
                "PROM_LEVEL\tNUMBER(1,0)\n" +
                "BONUS_POINT\tNUMBER(5,0)\n" +
                "BLOCK\tNUMBER(1,0)\n" +
                "LIMIT_QTY\tNUMBER(3,0)\n" +
                "PROM_TXT\tVARCHAR2(30)\n" +
                "MEMO\tVARCHAR2(30)";
        List<ExcelRow> excelRowFromOracle =
                SourceUtil.getExcelRowFromOracle(excelColumn);
        ProcGrExcelRowListWrapper procGrExcelRowListWrapper = ProcGrExcelRowListWrapper.of(excelRowFromOracle);
        System.out.println(procGrExcelRowListWrapper.getSqlFetchStatement());
    }

    @Test
    void getCopyValue() {
        String excelColumn = "PROMOTION_NO\tNUMBER(4,0)\n" +
                "PROM_TYPE\tVARCHAR2(1)\n" +
                "PROM_LEVEL\tNUMBER(1,0)\n" +
                "BONUS_POINT\tNUMBER(5,0)\n" +
                "BLOCK\tNUMBER(1,0)\n" +
                "LIMIT_QTY\tNUMBER(3,0)\n" +
                "PROM_TXT\tVARCHAR2(30)\n" +
                "MEMO\tVARCHAR2(30)";
        List<ExcelRow> excelRowFromOracle =
                SourceUtil.getExcelRowFromOracle(excelColumn);
        ProcGrExcelRowListWrapper procGrExcelRowListWrapper = ProcGrExcelRowListWrapper.of(excelRowFromOracle);
        System.out.println(procGrExcelRowListWrapper.getCopyValue());
    }

    @Test
    void getPropertyNameList() {
        String excelColumn = "PROMOTION_NO\tNUMBER(4,0)\n" +
                "PROM_TYPE\tVARCHAR2(1)\n" +
                "PROM_LEVEL\tNUMBER(1,0)\n" +
                "BONUS_POINT\tNUMBER(5,0)\n" +
                "BLOCK\tNUMBER(1,0)\n" +
                "LIMIT_QTY\tNUMBER(3,0)\n" +
                "PROM_TXT\tVARCHAR2(30)\n" +
                "MEMO\tVARCHAR2(30)";
        List<ExcelRow> excelRowFromOracle =
                SourceUtil.getExcelRowFromOracle(excelColumn);
        ProcGrExcelRowListWrapper procGrExcelRowListWrapper = ProcGrExcelRowListWrapper.of(excelRowFromOracle);
        System.out.println(procGrExcelRowListWrapper.getPropertyNameList());
    }

    @Test
    void startDate() {
        LocalDate startDate = DateUtil.getDateFromString("2022-01-01");
        List<String> startDateList = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            startDateList.add(DateUtil.getStringFromDate(startDate));
            startDate = startDate.plusMonths(1);
        }
        System.out.println(startDateList);
    }
    @Test
    void endDate() {
        LocalDate endDate = DateUtil.getDateFromString("2022-02-01");
        List<String> endDateList = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            endDateList.add(DateUtil.getStringFromDate(endDate));
            endDate = endDate.plusMonths(1);
        }
        endDateList.add("2023-11-28");
        System.out.println(endDateList);
    }
}
