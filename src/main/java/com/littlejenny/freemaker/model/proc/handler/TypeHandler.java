package com.littlejenny.freemaker.model.proc.handler;

import com.littlejenny.freemaker.model.proc.chain.TypeChain;

import java.util.List;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/12/1
 */
public abstract class TypeHandler extends TypeChain {
    protected TypeChain chainHead;
    protected List<TypeChain> chainList;

    public TypeHandler() {

    }

    @Override
    public boolean canHandle(String type) {
        for (TypeChain chain : chainList) {
            if (chain.canHandle(type)) return true;
        }
        return false;
    }

    @Override
    public String getReadType(String type) {
        return chainHead.getReadType(type);
    }

    @Override
    public String getWriteType(String type) {
        return chainHead.getWriteType(type);
    }

    @Override
    public String getWriteFormatSyntax(String type) {
        return chainHead.getWriteFormatSyntax(type);
    }

    public Integer length(String type) {
        return chainHead.length(type);
    }
}
