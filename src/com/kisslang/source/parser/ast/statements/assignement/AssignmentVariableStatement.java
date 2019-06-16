package com.kisslang.source.parser.ast.statements.assignement;

import com.kisslang.source.library.keys.VariableKey;
import com.kisslang.source.library.Variables;
import com.kisslang.source.library.Value;
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

public final class AssignmentVariableStatement implements Statement {


    private String variableName;

    private Expression expression;

    private Value value=null;

    private boolean immutable=false;

    boolean valueIsAlreadyProvided;

    public AssignmentVariableStatement(String variableName, Expression expression){
        this.variableName=variableName;
        this.expression=expression;
        this.valueIsAlreadyProvided=false;
    }

    @Override
    public void execute() {

        if(valueIsAlreadyProvided){
            VariableKey varToBeAdded=new VariableKey(variableName,immutable);
            Variables.add(varToBeAdded,value);
            return;
        }
        Value value1=expression.eval();
        VariableKey varToBeAdded=new VariableKey(variableName,immutable);
        Variables.add(varToBeAdded,value1);
    }

    @Override
    public String toString() {
        return variableName+" = "+expression.eval();
    }

    public AssignmentVariableStatement(String variableName, Value value){
        this.variableName=variableName;
        this.value=value;
        this.valueIsAlreadyProvided=true;
    }

}
