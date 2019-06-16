package com.kisslang.source.parser.ast.expression;

import com.kisslang.source.library.*;
import com.kisslang.source.library.keys.FunctionKey;
import com.kisslang.source.library.keys.VariableKey;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (C) 2019 The KISSlang Project by Vitalii Vorobii
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

public class ImmutableFunctionalCallExpression implements Expression {

    private final String funcName;

    private final List<Expression> args;

    public ImmutableFunctionalCallExpression(String funcName, List<Expression> args){
        this.funcName=funcName;
        this.args=args;
    }

    private int getArgumentsCount(){
        return args.size();
    }

    public ImmutableFunctionalCallExpression(String funcName){
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

        Function func=Functions.get(new FunctionKey(funcName,length,true));

        if ( func instanceof UserDefinedFunction){

            UserDefinedFunction userFunc=(UserDefinedFunction) func;

            if(length!=userFunc.getArgumentsCount()){
                throw new RuntimeException("Arguments don`t match. ");
            }

            for (int i=0;i<userFunc.getArgumentsCount();i++){
                Variables.add(new VariableKey(userFunc.getArgName(i).getArgumentName(),userFunc.getArgName(i).isImmutable()),values[i]);
            }

        }

        return func.execute(values);
    }



    @Override
    public String toString() {
        return super.toString();
    }

}
