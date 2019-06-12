package com.kisslang.source.parser.ast;

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
        double value=expression.eval();
        Variables.add(variableName,value);
    }

    @Override
    public String toString() {
        return variableName+" = "+expression.eval();
    }
}
