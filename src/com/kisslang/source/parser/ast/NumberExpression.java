package com.kisslang.source.parser.ast;

public class NumberExpression implements Expression {

    private final double value;

    public NumberExpression(double value){
        this.value=value;
    }

    public double eval(){
        return value;
    }
}
