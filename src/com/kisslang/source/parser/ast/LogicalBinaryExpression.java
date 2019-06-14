package com.kisslang.source.parser.ast;

import com.kisslang.source.library.LogicalValue;
import com.kisslang.source.library.Value;

public class LogicalBinaryExpression implements Expression{

    private final Expression expr1, expr2;
    private final String operation;

    public LogicalBinaryExpression(String operation, Expression expr1, Expression expr2) {
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    private boolean doubleAsBoolean(Value val){
        return val.asDouble()==new LogicalValue(true).asDouble();
    }

    @Override
    public Value eval() {

        boolean left=expr1.eval().asBoolean();
        boolean right=expr2.eval().asBoolean();

        switch (operation) {

            case "&&": return new LogicalValue(left && right);

            case "&": return new LogicalValue(left & right);

            case "|": return new LogicalValue(left | right);

            case "||": return new LogicalValue( left || right);

            default:
                return new LogicalValue(left && right);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s %c %s]", expr1, operation, expr2);
    }
}
