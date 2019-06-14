package com.kisslang.source.parser.ast.expression;

import com.kisslang.source.library.value.NumberValue;
import com.kisslang.source.library.value.Value;

public final class UnaryExpression implements Expression {

    private final Expression expr1;

    private final char operation;

    public UnaryExpression(char operation, Expression expr1) {
        this.operation = operation;
        this.expr1 = expr1;
    }

    @Override
    public Value eval() {
        switch (operation) {
            case '-': return new NumberValue( -expr1.eval().asDouble());
            case '+':
            default:
                return new NumberValue(expr1.eval().asDouble());
        }
    }

    @Override
    public String toString() {
        return String.format("%c %s", operation, expr1);
    }
}