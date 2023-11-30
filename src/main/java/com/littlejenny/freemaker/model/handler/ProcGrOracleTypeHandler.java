package com.littlejenny.freemaker.model.handler;

import com.littlejenny.freemaker.model.ProcType;
import com.littlejenny.freemaker.model.chain.TypeChain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/20
 */
public class ProcGrOracleTypeHandler {

    private final TypeChain chainHead;
    private final List<TypeChain> chainList;

    public ProcGrOracleTypeHandler() {
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

    public boolean canHandle(String type) {
        for (TypeChain chain : chainList) {
            if (chain.canHandle(type)) return true;
        }
        return false;
    }

    public String getReadType(String type) {
        return chainHead.getReadType(type);
    }

    public String getWriteType(String type) {
        return chainHead.getWriteType(type);
    }
    public String getWriteFormatSyntax(String type){
        return chainHead.getWriteFormatSyntax(type);
    }
    public int length(String type) {
        return chainHead.length(type);
    }

    private class OracleDateType extends TypeChain {

        public OracleDateType(TypeChain next) {
            super(next, ProcType.VARCHAR.name(), ProcType.CHAR.name().toLowerCase(),'s');
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
            super(next, ProcType.VARCHAR.name(), ProcType.CHAR.name().toLowerCase(),'s');
        }

        @Override
        public boolean canHandle(String type) {
            return type.toLowerCase().contains("varchar2");
        }

    }

    private class OracleNumberType extends TypeChain {

        public OracleNumberType(TypeChain next) {
            super(next, ProcType.VARCHAR.name(), ProcType.CHAR.name().toLowerCase(),'s');
        }

        @Override
        public boolean canHandle(String type) {
            return type.toLowerCase().contains("number");
        }

    }

    private class OracleDateTimeType extends TypeChain {

        public OracleDateTimeType(TypeChain next) {
            super(next, ProcType.VARCHAR.name(), ProcType.CHAR.name().toLowerCase(),'s');
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
