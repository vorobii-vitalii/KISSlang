package com.kisslang.source.parser.ast;

import com.kisslang.source.library.LogicalValue;
import com.kisslang.source.library.Value;

public class LogicalUnaryExpression implements Expression {
    private final Expression expr1;

    private final char operation;

    public LogicalUnaryExpression(char operation, Expression expr1) {
        this.operation = operation;
        this.expr1 = expr1;
    }

    @Override
    public Value eval() {
        switch (operation) {
            case '!': return new LogicalValue(!expr1.eval().asBoolean() );
            default:
                return new LogicalValue(expr1.eval().asBoolean());
        }
    }

    @Override
    public String toString() {
        return String.format("%c %s", operation, expr1);
    }
}
