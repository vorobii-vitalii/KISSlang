package com.kisslang.source.parser.ast.expression.binary;

import com.kisslang.source.library.value.built_in.bool.LogicalValue;
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

public class LogicalBinaryExpression implements Expression {

    private final Expression expr1, expr2;
    private final String operation;

    public LogicalBinaryExpression(String operation, Expression expr1, Expression expr2) {
        this.operation = operation;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    private boolean doubleAsBoolean(Value val){
        return val.asDouble()==new LogicalValue(true).asDouble();
    }

    @Override
    public Value eval() {

        boolean left=expr1.eval().asBoolean();
        boolean right=expr2.eval().asBoolean();

        switch (operation) {

            case "&&": return new LogicalValue(left && right);

            case "&": return new LogicalValue(left & right);

            case "|": return new LogicalValue(left | right);

            case "||": return new LogicalValue( left || right);

            default:
                return new LogicalValue(left && right);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s %c %s]", expr1, operation, expr2);
    }
}
