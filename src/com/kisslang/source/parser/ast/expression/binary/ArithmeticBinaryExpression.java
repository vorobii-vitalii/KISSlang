package com.kisslang.source.parser.ast.expression.binary;

import com.kisslang.source.library.value.built_in.number.NumberValue;
import com.kisslang.source.library.value.built_in.string.StringValue;
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

public final class ArithmeticBinaryExpression implements Expression {

    private final Expression expr1, expr2;
    private final char operation;

    public ArithmeticBinaryExpression(char operation, Expression expr1, Expression expr2) {
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    private static double getPow(double k,double n){
        double result=Math.pow(k,n);
        return result;
    }

    @Override
    public Value eval() {

        switch (operation) {
            case '-': return new NumberValue(expr1.eval().asNumber() - expr2.eval().asNumber());
            case '*': return new NumberValue( expr1.eval().asNumber() * expr2.eval().asNumber());
            case '/': return new NumberValue( expr1.eval().asNumber() / expr2.eval().asNumber());
            case '^': return new NumberValue( getPow(expr1.eval().asNumber(),expr2.eval().asNumber()));
            case '+':
            default:
                if( expr1.eval().isString() || expr2.eval().isString()){
                    return new StringValue(expr1.eval().asString() + expr2.eval().asString());
                }
                return new NumberValue(expr1.eval().asNumber() + expr2.eval().asNumber());
        }
    }

    @Override
    public String toString() {
        return String.format("[%s %c %s]", expr1, operation, expr2);
    }
}