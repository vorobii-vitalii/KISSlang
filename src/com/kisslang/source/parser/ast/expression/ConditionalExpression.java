package com.kisslang.source.parser.ast.expression;

import com.kisslang.source.library.value.LogicalValue;
import com.kisslang.source.library.value.Value;

public class ConditionalExpression implements Expression {

    private Expression expr1;
    private Expression expr2;
    private String operation;

    public ConditionalExpression(String operation,Expression expr1,Expression expr2){
        this.operation=operation;
        this.expr1=expr1;
        this.expr2=expr2;
    }

    @Override
    public Value eval() {

        Value result;

        switch (operation){

            case ">":
                result = new LogicalValue(expr1.eval().asDouble()>expr2.eval().asDouble());
                break;
            case ">=":
                result = new LogicalValue(expr1.eval().asDouble()>=expr2.eval().asDouble());
                break;
            case "<":
                result = new LogicalValue(expr1.eval().asDouble()<expr2.eval().asDouble());
                break;
            case "<=":
                result = new LogicalValue(expr1.eval().asDouble()<=expr2.eval().asDouble());
                break;
            case "==":
                result = new LogicalValue(expr1.eval().asDouble()==expr2.eval().asDouble());
                break;
            case "!=":
                result = new LogicalValue(expr1.eval().asDouble()!=expr2.eval().asDouble());
                break;
            default:
                result=new LogicalValue(false);
                break;
        }

        return result;
    }
}