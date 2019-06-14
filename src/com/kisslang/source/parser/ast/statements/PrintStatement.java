package com.kisslang.source.parser.ast.statements;

import com.kisslang.source.parser.ast.expression.Expression;

public class PrintStatement implements Statement {

    Expression expr1;


    public PrintStatement(Expression expr1){
        this.expr1=expr1;
    }

    @Override
    public void execute() {
        System.out.println(expr1.eval().asString());
    }

    @Override
    public String toString() {
        return "print : "+expr1.toString();
    }
}
