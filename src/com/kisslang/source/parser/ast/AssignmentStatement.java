package com.kisslang.source.parser.ast;

import com.kisslang.source.library.NumberValue;
import com.kisslang.source.library.Value;
import com.kisslang.source.library.Variables;

public class AssignmentStatement implements Statement {


    private final String variableName;

    private final Expression expression;

    public AssignmentStatement(String variableName,Expression expression){
        this.variableName=variableName;
        this.expression=expression;
    }

    @Override
    public void execute() {
        Value value=new NumberValue(expression.eval().asDouble());
        Variables.add(variableName,value);
    }

    @Override
    public String toString() {
        return variableName+" = "+expression.eval();
    }
}
