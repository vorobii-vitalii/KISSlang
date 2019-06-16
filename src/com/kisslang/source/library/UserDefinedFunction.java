package com.kisslang.source.library;

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

import com.kisslang.source.library.value.built_in.bool.LogicalValue;
import com.kisslang.source.parser.ast.statements.Argument;
import com.kisslang.source.parser.ast.statements.Statement;

import java.util.List;

public class UserDefinedFunction implements Function {

    private final List<Argument> argNames;

    private final Statement functionBody;

    public UserDefinedFunction(List<Argument> argNames, Statement functionBody){
        this.argNames=argNames;
        this.functionBody=functionBody;
    }

    public int getArgumentsCount(){
        return argNames.size();
    }

    public Argument getArgName(int index){

        if(index<0 || index>=getArgumentsCount()){
            return null;
        }

        return argNames.get(index);

    }

    @Override
    public Value execute(Value[] args) {
        functionBody.execute();
        return new LogicalValue(false);
    }
}
