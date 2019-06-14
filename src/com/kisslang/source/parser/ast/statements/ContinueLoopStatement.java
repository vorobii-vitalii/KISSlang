package com.kisslang.source.parser.ast.statements;

public class ContinueLoopStatement extends RuntimeException implements Statement {

    @Override
    public void execute() {
        throw this;
    }

    @Override
    public String toString() {
        return "Continue";
    }
}
