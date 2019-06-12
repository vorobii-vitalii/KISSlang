package com.kisslang.source.parser.ast;

import java.rmi.server.ExportException;

public class BinaryExpression implements Expression {

    private Expression expr1,expr2;
    private final char operation;

    public BinaryExpression(char operation,Expression expr1,Expression expr2){
        this.expr1=expr1;
        this.expr2=expr2;
        this.operation=operation;
    }

    public Expression getFirstExpression(){
        return expr1;
    }

    public Expression getSecondExpression(){
        return expr2;
    }


    public double eval(){

        switch (operation){
            case '+': return expr1.eval()+expr2.eval();
            case '-': return expr1.eval()-expr2.eval();
            case '*': return expr1.eval()*expr2.eval();
            case '/': return expr1.eval()/expr2.eval();

            default:
                return expr1.eval()/expr2.eval();

        }
    }

    public String toString(){
        return String.format("%s %c %s ",expr1,operation,expr2);
    }


}
