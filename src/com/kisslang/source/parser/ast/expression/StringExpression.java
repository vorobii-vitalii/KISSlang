package com.kisslang.source.parser.ast.expression;

import com.kisslang.source.library.value.StringValue;
import com.kisslang.source.library.value.Value;

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