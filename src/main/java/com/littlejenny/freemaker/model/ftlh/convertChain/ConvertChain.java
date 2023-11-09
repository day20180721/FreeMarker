package com.littlejenny.freemaker.model.ftlh.convertChain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class ConvertChain {
    private String source;
    private String target;
    private ConvertChain next;

    protected boolean canConvert(String type) {
        return source.equals(type);
    }

    public String convert(String type) {
        if (canConvert(type)) {
            return convertImpl(type);
        } else {
            return next.convert(type);
        }
    }

    protected String convertImpl(String type) {
        return target;
    }
}
