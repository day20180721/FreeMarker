package com.littlejenny.freemaker.model.proc.handler.oracle;

import com.littlejenny.freemaker.model.proc.ProcType;
import com.littlejenny.freemaker.model.proc.chain.TypeChain;
import com.littlejenny.freemaker.model.proc.handler.TypeHandler;

import java.util.ArrayList;

/**
 * TypeChain的實例化，用於判斷某個資料庫的型態是否能夠判斷
 *
 * @author 王洪棟 - Lin
 * @created date 2023/11/20
 */
public class OracleToProcGrTypeHandler extends TypeHandler {
    public OracleToProcGrTypeHandler() {
        TypeChain oracleDateTimeType = new OracleDateTimeType(null);
        TypeChain oracleDateType = new OracleDateType(oracleDateTimeType);
        TypeChain oracleNumberType = new OracleNumberType(oracleDateType);
        TypeChain oracleVarchar2Type = new OracleVarchar2Type(oracleNumberType);

        chainHead = oracleVarchar2Type;

        chainList = new ArrayList<>();
        chainList.add(oracleDateTimeType);
        chainList.add(oracleDateType);
        chainList.add(oracleNumberType);
        chainList.add(oracleVarchar2Type);
    }

    private class OracleDateType extends TypeChain {

        public OracleDateType(TypeChain next) {
            super(next, ProcType.VARCHAR.name(), ProcType.CHAR.name().toLowerCase(), 's');
        }

        @Override
        public boolean canHandle(String type) {
            return type.toLowerCase().contains("date");
        }

        @Override
        public Integer length(String type) {
            if (!canHandle(type)) {
                if (next == null) return null;
                return next.length(type);
            }
            return 30;
        }
    }

    private class OracleVarchar2Type extends TypeChain {
        public OracleVarchar2Type(TypeChain next) {
            super(next, ProcType.VARCHAR.name(), ProcType.CHAR.name().toLowerCase(), 's');
        }

        @Override
        public boolean canHandle(String type) {
            return type.toLowerCase().contains("varchar2");
        }

    }

    private class OracleNumberType extends TypeChain {

        public OracleNumberType(TypeChain next) {
            super(next, ProcType.VARCHAR.name(), ProcType.CHAR.name().toLowerCase(), 's');
        }

        @Override
        public boolean canHandle(String type) {
            return type.toLowerCase().contains("number");
        }

    }

    private class OracleDateTimeType extends TypeChain {

        public OracleDateTimeType(TypeChain next) {
            super(next, ProcType.VARCHAR.name(), ProcType.CHAR.name().toLowerCase(), 's');
        }

        @Override
        public boolean canHandle(String type) {
            return type.toLowerCase().contains("datetime");
        }

        @Override
        public Integer length(String type) {
            if (!canHandle(type)) {
                if (next == null) return null;
                return next.length(type);
            }
            return 30;
        }
    }
}
