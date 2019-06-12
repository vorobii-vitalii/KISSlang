package com.kisslang.source.parser.ast;

import com.kisslang.source.library.StringValue;
import com.kisslang.source.library.Value;

public class StringExpression implements Expression {

    private final String value;

    public StringExpression(String value){
        this.value=value;
    }

    @Override
    public Value eval() {
        return new StringValue(value);
    }

    @Override
    public String toString() {
        return value;
    }

}
