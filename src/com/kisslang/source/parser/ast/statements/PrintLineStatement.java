package com.kisslang.source.parser.ast.statements;

import com.kisslang.source.parser.ast.expression.Expression;

public class PrintLineStatement implements Statement {

    Expression expr1;

    public PrintLineStatement(Expression expr1){
        this.expr1=expr1;
    }

    @Override
    public void  execute() {
        System.out.println(expr1.eval().asString());
    }

    @Override
    public String toString() {
        return "PrintLine : "+expr1.toString();
    }
}
