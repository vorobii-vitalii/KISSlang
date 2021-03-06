package com.kisslang.source.parser.ast.statements.assignement;

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

public abstract class AssignementStatement implements Statement {

    private String variableName;

    private Expression expression = null;

    private Value value = null;

    private boolean immutable = false;

    boolean valueIsAlreadyProvided;


    @Override
    public String toString () {
        return variableName + " = " + expression.eval ( );
    }

}
