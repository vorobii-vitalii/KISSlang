package com.kisslang.source.parser.ast;

import com.kisslang.source.library.Value;
import com.kisslang.source.library.Variables;

public class ConstantExpression implements Expression {

    private final String text;

    public ConstantExpression(String text){
        this.text=text;
    }

    @Override
    public Value eval() {
        if(Variables.isExists(text)){
            return Variables.get(text);
        }
        throw new RuntimeException("There is no such constant!");
    }

    @Override
    public String toString() {
        return text+" "+eval();
    }
}
