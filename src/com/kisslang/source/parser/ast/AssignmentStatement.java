package com.kisslang.source.parser.ast;

import com.kisslang.source.library.NumberValue;
import com.kisslang.source.library.StringValue;
import com.kisslang.source.library.Value;
import com.kisslang.source.library.Variables;

public class AssignmentStatement implements Statement {


    protected final String variableName;

    protected final Expression expression;

    public AssignmentStatement(String variableName,Expression expression){
        this.variableName=variableName;
        this.expression=expression;
    }

    @Override
    public void execute() {

        Value value;

        if (expression.eval().isString()){
                value=new StringValue(expression.eval().asString());
        }
        else {
                value=new NumberValue(expression.eval().asDouble());
        }

        Variables.add(variableName,value);
    }

    @Override
    public String toString() {
        return variableName+" = "+expression.eval();
    }
}
