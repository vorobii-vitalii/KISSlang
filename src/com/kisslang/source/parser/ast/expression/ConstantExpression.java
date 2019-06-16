package com.kisslang.source.parser.ast.expression;

import com.kisslang.source.library.keys.VariableKey;
import com.kisslang.source.library.Value;
import com.kisslang.source.library.Variables;

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

public class ConstantExpression implements Expression {

    private final String text;

    public ConstantExpression(String text){
        this.text=text;
    }

    @Override
    public Value eval() {

        VariableKey key=new VariableKey(text,true);

        if (Variables.isExists(key)) {
            return Variables.get(key);
        }

        throw new RuntimeException("There is no such constant! -> "+text);
    }

    @Override
    public String toString() {
        return text+" "+eval();
    }
}
