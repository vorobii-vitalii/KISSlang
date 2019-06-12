package com.kisslang.source.parser.ast;

public class UnaryExpression implements Expression {

    private final Expression expr1;

    private final char operation;

    public UnaryExpression(char operation,Expression expr1){
        this.operation=operation;
        this.expr1=expr1;
    }

    public double eval(){

        if(operation=='-'){
            return -expr1.eval();
        }
        else{
            return expr1.eval();
        }
    }

    public String toString(){
        return "Operation : "+operation+" -> "+Double.toString(expr1.eval());
    }
}
