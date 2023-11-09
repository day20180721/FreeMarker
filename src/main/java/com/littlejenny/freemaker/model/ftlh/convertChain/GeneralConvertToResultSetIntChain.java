package com.littlejenny.freemaker.model.ftlh.convertChain;

public class GeneralConvertToResultSetIntChain extends ConvertChain {
    @Override
    protected boolean canConvert(String type) {
        return true;
    }
    @Override
    protected String convertImpl(String type) {
        return "Object";
    }
}
