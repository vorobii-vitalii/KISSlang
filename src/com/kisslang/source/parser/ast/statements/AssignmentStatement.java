package com.kisslang.source.parser.ast.statements;

import com.kisslang.source.library.value.Value;
import com.kisslang.source.library.Variables;
import com.kisslang.source.parser.ast.expression.Expression;

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

        //System.out.println(expression.eval());

        Variables.add(variableName,value);
    }

    @Override
    public String toString() {
        return variableName+" = "+expression.eval();
    }
}
