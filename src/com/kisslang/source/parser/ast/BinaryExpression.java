package com.kisslang.source.parser.ast;

import com.kisslang.source.library.NumberValue;
import com.kisslang.source.library.Value;

import java.rmi.server.ExportException;

public final class BinaryExpression implements Expression {

    private final Expression expr1, expr2;
    private final char operation;

    public BinaryExpression(char operation, Expression expr1, Expression expr2) {
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public Value eval() {
        switch (operation) {
            case '-': return new NumberValue(expr1.eval().asDouble() - expr2.eval().asDouble());
            case '*': return new NumberValue( expr1.eval().asDouble() * expr2.eval().asDouble());
            case '/': return new NumberValue( expr1.eval().asDouble() / expr2.eval().asDouble());
            case '+':
            default:
                return new NumberValue(expr1.eval().asDouble() + expr2.eval().asDouble());
        }
    }

    @Override
    public String toString() {
        return String.format("[%s %c %s]", expr1, operation, expr2);
    }
}