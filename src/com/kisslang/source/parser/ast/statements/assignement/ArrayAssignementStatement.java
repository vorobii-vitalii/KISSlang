package com.kisslang.source.parser.ast.statements.assignement;

import com.kisslang.source.library.value.built_in.array.ArrayValue;
import com.kisslang.source.library.Value;
import com.kisslang.source.library.Variables;
import com.kisslang.source.library.keys.VariableKey;
import com.kisslang.source.parser.ast.expression.Expression;
import com.kisslang.source.parser.ast.statements.Statement;

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

public class ArrayAssignementStatement implements Statement {

    private String variableName;

    private Value[] indexes;

    private Expression toAssign;


    public ArrayAssignementStatement ( String variableName , Value[] indexes , Expression toAssign ) {
        this.indexes = indexes;
        this.variableName = variableName;
        this.toAssign = toAssign;
    }

    @Override
    public void execute () {

        Value value = Variables.get ( new VariableKey ( variableName , true ) );

        ArrayValue arrValue = (ArrayValue) value;

        for (int i = 0; i < indexes.length - 1; i++) {
            arrValue = (ArrayValue) arrValue.getElement ( (int) indexes[i].asNumber ( ) );
        }
        arrValue.setElement ( (int) indexes[indexes.length - 1].asNumber ( ) , toAssign.eval ( ) );
    }


}
