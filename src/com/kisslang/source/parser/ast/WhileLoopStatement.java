package com.kisslang.source.parser.ast;

public class WhileLoopStatement implements Statement {

    private final Expression expression;

    private final Statement blockOfStatements;

    public WhileLoopStatement(Expression expression, Statement blockOfStatements){
        this.expression=expression;
        this.blockOfStatements=blockOfStatements;
    }

    @Override
    public void execute() {

        while(expression.eval().asBoolean()==true){
            blockOfStatements.execute();
        }

    }

    @Override
    public String toString() {
        return expression+" "+blockOfStatements;
    }
}
