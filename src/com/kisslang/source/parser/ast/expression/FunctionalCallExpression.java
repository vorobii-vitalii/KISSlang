package com.kisslang.source.parser.ast.expression;

import com.kisslang.source.library.value.built_in.Value;

import java.util.LinkedList;
import java.util.List;

public class FunctionalCallExpression implements Expression {

    private final String funcName;

    private final List<Expression> args;

    FunctionalCallExpression(String funcName,List<Expression> args){
        this.funcName=funcName;
        this.args=args;
    }

    FunctionalCallExpression(String funcName){
        this.funcName=funcName;
        this.args=new LinkedList<>();
    }

    public void addArgument(Expression arg){
        args.add(arg);
    }

    @Override
    public Value eval() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
