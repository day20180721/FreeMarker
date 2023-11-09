package com.littlejenny.freemaker.model.ftlh.convertChain;

public class IntegerConvertToResultSetIntChain extends ConvertChain {
    @Override
    protected boolean canConvert(String type) {
        return type.equals("Integer");
    }

    @Override
    protected String convertImpl(String type) {
        return "int";
    }
}
