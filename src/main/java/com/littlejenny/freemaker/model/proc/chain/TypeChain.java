package com.littlejenny.freemaker.model.proc.chain;

/**
 * 提供資料庫型態後，可以取得Proc中讀取及寫入的型態，我用於GR DO
 * @author 王洪棟 - Lin
 * @created date 2023/11/20
 */
public abstract class TypeChain {
    protected TypeChain next;
    /**
     * Proc用來讀的型態
     */
    protected String readProcType;
    /**
     * Proc用來寫的型態
     */
    protected String writeProcType;
    protected char writeFormatSyntax;
    public TypeChain(){

    }
    public TypeChain(TypeChain next, String readProcType, String writeProcType, char writeFormatSyntax) {
        this.next = next;
        this.readProcType = readProcType;
        this.writeProcType = writeProcType;
        this.writeFormatSyntax = writeFormatSyntax;
    }

    /**
     *
     * @param type 資料庫 Data type
     * @return
     */
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

    public String getWriteFormatSyntax(String type) {
        if (!canHandle(type)) {
            if (next == null) return null;
            return next.getWriteFormatSyntax(type);
        }
        return "%" + writeFormatSyntax + "||";
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
}
