package com.kisslang.source.parser.ast.expression.literal;

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

import com.kisslang.source.library.Value;
import com.kisslang.source.library.value.built_in.bool.LogicalValue;
import com.kisslang.source.parser.ast.expression.Expression;

public class BooleanExpression implements Expression {

    private boolean value;

    public BooleanExpression ( boolean value ) {
        this.value = value;
    }

    @Override
    public Value eval () {
        return new LogicalValue ( value );
    }
}
