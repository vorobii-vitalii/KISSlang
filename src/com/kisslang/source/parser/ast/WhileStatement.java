package com.kisslang.source.parser.ast;

public class WhileStatement implements Statement {

    private final Expression expression;

    private final Statement blockOfStatements;

    public WhileStatement(Expression expression,Statement blockOfStatements){
        this.expression=expression;
        this.blockOfStatements=blockOfStatements;
    }

    @Override
    public void execute() {
        while(expression.eval().asBoolean()==true){
            blockOfStatements.execute();
//            System.out.println(blockOfStatements);
        }

    }

    @Override
    public String toString() {
        return expression+" "+blockOfStatements;
    }
}
