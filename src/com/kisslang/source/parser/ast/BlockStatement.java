package com.kisslang.source.parser.ast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BlockStatement implements Statement {

    private List<Statement> statements;

    public BlockStatement(List<Statement> statements){
        this.statements=statements;
    }

    public BlockStatement(){
        this.statements=new LinkedList<>();
    }

    public void addStatement(Statement statement){
        statements.add(statement);
    }

    @Override
    public void execute() {

        for (Statement s: statements) {
            s.execute();
        }

    }

    @Override
    public String toString() {
        StringBuilder strBuild=new StringBuilder();
        for (Statement s: statements){
            strBuild.append(s);
        }
        return strBuild.toString();
    }
}
