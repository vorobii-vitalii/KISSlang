package com.kisslang.source.parser.ast;

public class ForLoopStatement implements Statement {

    private final Statement init;

    private final Expression expression;

    private final Statement afterExpression;

    private final Statement blockOfStatements;

    public ForLoopStatement(Statement init,Expression expression,Statement afterExpression,Statement blockOfStatements){
        this.init=init;
        this.expression=expression;
        this.afterExpression=afterExpression;
        this.blockOfStatements=blockOfStatements;
    }

    @Override
    public void execute() {

        for(init.execute();expression.eval().asBoolean()!=false;afterExpression.execute()){
            try {
                blockOfStatements.execute();
            }
            catch (BreakLoopStatement e){
                break;
            }
            catch (ContinueLoopStatement e){
                continue;
            }
        }

    }
}
