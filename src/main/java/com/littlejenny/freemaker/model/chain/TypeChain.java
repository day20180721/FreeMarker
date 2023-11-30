package com.littlejenny.freemaker.model.chain;

import com.littlejenny.freemaker.model.ProcType;
import lombok.Data;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/20
 */
public abstract class TypeChain {
    protected TypeChain next;
    protected String readProcType;
    protected String writeProcType;
    protected char writeFormatSyntax;

    public TypeChain(TypeChain next, String readProcType, String writeProcType, char writeFormatSyntax) {
        this.next = next;
        this.readProcType = readProcType;
        this.writeProcType = writeProcType;
        this.writeFormatSyntax = writeFormatSyntax;
    }

    public boolean canHandle(String type) {
        return true;
    }

    public String getReadType(String type) {
        if (!canHandle(type)) {
            if (next == null) return null;
            return next.getReadType(type);
        }
        return readProcType;
    }

    public String getWriteType(String type) {
        if (!canHandle(type)) {
            if (next == null) return null;
            return next.getWriteType(type);
        }
        return writeProcType;
    }


    public Integer length(String type) {
        if (!canHandle(type)) {
            if (next == null) return null;
            return next.length(type);
        }

        int propertyLength = -1;

        int leftParenthesesIndex = type.indexOf('(');
        int rightParenthesesIndex = type.lastIndexOf(')');

        if (leftParenthesesIndex != -1 && rightParenthesesIndex != -1) {
            String substring = type.substring(leftParenthesesIndex + 1, rightParenthesesIndex);
            propertyLength = Integer.parseInt(substring.split(",")[0]);
        }
        //+2 For '\0'
        return propertyLength + 2;
    }

    public String getWriteFormatSyntax(String type) {
        if (!canHandle(type)) {
            if (next == null) return null;
            return next.getWriteFormatSyntax(type);
        }
        return "%" + writeFormatSyntax + "||";
    }
}
