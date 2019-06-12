package com.kisslang.source.parser.ast;

public class PrintStatement implements Statement {

    Expression expr1;


    public PrintStatement(Expression expr1){
        this.expr1=expr1;
    }

    @Override
    public void execute() {
        System.out.println(expr1.eval());
    }

    @Override
    public String toString() {
        return "print : "+expr1.toString();
    }
}
