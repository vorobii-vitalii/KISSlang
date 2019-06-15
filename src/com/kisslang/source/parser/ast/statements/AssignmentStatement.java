package com.kisslang.source.parser.ast.statements;

import com.kisslang.source.library.value.Value;
import com.kisslang.source.library.Variables;
import com.kisslang.source.parser.ast.expression.Expression;

public final class AssignmentStatement implements Statement {


    private final String variableName;

    private Expression expression;

    private Value value=null;

    boolean valueIsAlreadyProvided;

    public AssignmentStatement(String variableName,Expression expression){
        this.variableName=variableName;
        this.expression=expression;
        this.valueIsAlreadyProvided=false;
    }

    public AssignmentStatement(String variableName,Value value){
        this.variableName=variableName;
        this.value=value;
        this.valueIsAlreadyProvided=true;
    }

    @Override
    public void execute() {

        if(valueIsAlreadyProvided){
            Variables.add(variableName,value);
            return;
        }
        Value value1=expression.eval();

        Variables.add(variableName,value1);
    }

    @Override
    public String toString() {
        return variableName+" = "+expression.eval();
    }
}
