package com.kisslang.source.parser.ast.statements;

import com.kisslang.source.parser.ast.expression.Expression;

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

    @Override
    public String toString() {
        return expression+" "+blockOfStatements;
    }
}
