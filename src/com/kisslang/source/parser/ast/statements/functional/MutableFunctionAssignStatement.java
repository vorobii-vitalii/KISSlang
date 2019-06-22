package com.kisslang.source.parser.ast.statements.functional;

import com.kisslang.source.library.Functions;
import com.kisslang.source.library.UserDefinedFunction;
import com.kisslang.source.library.keys.FunctionKey;
import com.kisslang.source.parser.ast.statements.Statement;

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

public class MutableFunctionAssignStatement implements Statement {

    private final String functionName;

    private final List<Argument> argNames;

    private final Statement functionBody;

    public MutableFunctionAssignStatement ( String functionName , List<Argument> argNames , Statement functionBody ) {
        this.functionBody = functionBody;
        this.functionName = functionName;
        this.argNames = argNames;
    }

    private int getArgumentsCount () {
        return argNames.size ( );
    }

    @Override
    public void execute () {
        FunctionKey key = new FunctionKey ( functionName , getArgumentsCount ( ) , false );
        Functions.add ( key , new UserDefinedFunction ( argNames , functionBody ) );
    }

    @Override
    public String toString () {
        return super.toString ( );
    }
}
