package com.kisslang.source.parser.ast.expression.unary;

import com.kisslang.source.library.value.built_in.number.NumberValue;
import com.kisslang.source.library.value.built_in.Value;
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

public final class UnaryExpression implements Expression {

    private final Expression expr1;

    private final char operation;

    public UnaryExpression(char operation, Expression expr1) {
        this.operation = operation;
        this.expr1 = expr1;
    }

    @Override
    public Value eval() {
        switch (operation) {
            case '-': return new NumberValue( -expr1.eval().asNumber());
            case '+':
            default:
                return new NumberValue(expr1.eval().asNumber());
        }
    }

    @Override
    public String toString() {
        return String.format("%c %s", operation, expr1);
    }
}