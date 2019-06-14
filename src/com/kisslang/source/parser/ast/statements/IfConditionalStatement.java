package com.kisslang.source.parser.ast.statements;

import com.kisslang.source.parser.ast.expression.Expression;

public class IfConditionalStatement implements Statement {

    private final Expression expression;
    private final Statement ifStatement;
    private final Statement elseStatement;

    public IfConditionalStatement(Expression expression,Statement ifStatement,Statement elseStatement){
        this.expression=expression;
        this.ifStatement=ifStatement;
        this.elseStatement=elseStatement;
    }


    @Override
    public void execute() {

        final boolean result=expression.eval().asBoolean();

        if(result==true){
            ifStatement.execute();
        }
        else{
            if(elseStatement!=null) {
                elseStatement.execute();
            }
        }

    }

    @Override
    public String toString() {
        return "If : "+ifStatement.toString();
    }
}
