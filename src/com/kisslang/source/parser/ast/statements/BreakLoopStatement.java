package com.kisslang.source.parser.ast.statements;

public class BreakLoopStatement extends RuntimeException implements Statement {

    @Override
    public void execute() {
        throw this;
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName();
    }
}
