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

import com.kisslang.source.library.ArrayValue;
import com.kisslang.source.library.ObjectValue;
import com.kisslang.source.library.Value;

import java.util.List;

public class ArrayCreateExpression implements Expression {

    private List<Expression> expressions;

    private boolean isEmpty(){
        return expressions.isEmpty();
    }

    public ArrayCreateExpression(List<Expression> expressions){
        this.expressions=expressions;
    }

    public ArrayCreateExpression(){
        throw new RuntimeException("Array cannot be null...");
    }

    @Override
    public Value eval() {
        Value [] arrayOfValues=new Value[expressions.size()];

        for (int i = 0; i < arrayOfValues.length ; i++) {
            arrayOfValues[i]=expressions.get(i).eval();
        }

        return new ArrayValue(arrayOfValues);

    }

}
