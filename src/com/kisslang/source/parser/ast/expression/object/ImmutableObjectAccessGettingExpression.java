package com.kisslang.source.parser.ast.expression.object;

import com.kisslang.source.library.value.built_in.object.ObjectValue;
import com.kisslang.source.library.Value;
import com.kisslang.source.library.Variables;
import com.kisslang.source.library.keys.VariableKey;
import com.kisslang.source.parser.ast.expression.Expression;

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

public class ImmutableObjectAccessGettingExpression implements Expression {

    private final String objectName;

    private final VariableKey field;

    public ImmutableObjectAccessGettingExpression ( String objectName , VariableKey field ) {
        this.objectName = objectName;
        this.field = field;
    }

    @Override
    public Value eval () {

        Value value = Variables.get ( new VariableKey ( objectName , true ) );

        ObjectValue objectValue = (ObjectValue) value;

        return objectValue.get ( field );

    }

}
