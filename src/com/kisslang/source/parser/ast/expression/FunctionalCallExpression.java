package com.kisslang.source.parser.ast.expression;

import com.kisslang.source.library.Functions;
import com.kisslang.source.library.Value;
import com.kisslang.source.library.keys.FunctionKey;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FunctionalCallExpression implements Expression {

    private final String funcName;

    private final List<Expression> args;

    public FunctionalCallExpression(String funcName,List<Expression> args){
        this.funcName=funcName;
        this.args=args;
    }

    private int getArgumentsCount(){
        return args.size();
    }

    public FunctionalCallExpression(String funcName){
        this.funcName=funcName;
        this.args=new ArrayList<>();
    }

    public void addArgument(Expression arg){
        args.add(arg);
    }

    @Override
    public Value eval() {
        int length=getArgumentsCount();

        Value [] values=new Value[length];

        for (int i=0;i<length;i++){
            values[i]=args.get(i).eval();
        }

        return Functions.get(new FunctionKey(funcName,length)).execute(values);
    }



    @Override
    public String toString() {
        return super.toString();
    }

}
