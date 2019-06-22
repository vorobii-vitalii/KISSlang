package com.kisslang.source.parser.ast.expression;

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

import com.kisslang.source.library.ObjectValue;
import com.kisslang.source.library.Value;
import com.kisslang.source.library.Variables;
import com.kisslang.source.library.keys.VariableKey;

public class ArrowBinaryExpression implements Expression {

    private final Expression expr1;

    private final VariableKey key;

    public ArrowBinaryExpression(Expression expr1,VariableKey key){
        this.expr1=expr1;
        this.key=key;
    }

    @Override
    public Value eval() {

        ObjectValue objectValue=(ObjectValue) expr1.eval ();

        System.out.println ("RESULT "+objectValue.get (key)+" of "+objectValue);
        return objectValue.get (key);

    }

}
