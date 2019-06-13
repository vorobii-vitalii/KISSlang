package com.kisslang.source.parser.ast;

import com.kisslang.source.library.Value;
import com.kisslang.source.library.Variables;

public final class AssignmentStatement implements Statement {


    private final String variableName;

    private final Expression expression;

    public AssignmentStatement(String variableName,Expression expression){
        this.variableName=variableName;
        this.expression=expression;
    }

    @Override
    public void execute() {

        Value value=expression.eval();

        Variables.add(variableName,value);
    }

    @Override
    public String toString() {
        return variableName+" = "+expression.eval();
    }
}
