package com.kisslang.source.parser.ast.expression;

import com.kisslang.source.library.value.NumberValue;
import com.kisslang.source.library.value.Value;

public final class NumberExpression implements Expression {

    private final double value;

    public NumberExpression(double value) {
        this.value = value;
    }

    @Override
    public Value eval() {
        return new NumberValue(value);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
