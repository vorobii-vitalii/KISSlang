package com.kisslang.source.parser.ast.expression;

import com.kisslang.source.library.keys.VariableKey;
import com.kisslang.source.library.Value;
import com.kisslang.source.library.Variables;

public class ConstantExpression implements Expression {

    private final String text;

    public ConstantExpression(String text){
        this.text=text;
    }

    @Override
    public Value eval() {

        VariableKey key=new VariableKey(text,true);

        if (Variables.isExists(key)) {
            return Variables.get(key);
        }

        throw new RuntimeException("There is no such constant! -> "+text);
    }

    @Override
    public String toString() {
        return text+" "+eval();
    }
}
