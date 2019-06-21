package com.kisslang.source.parser.ast.expression;

import com.kisslang.source.library.ObjectValue;
import com.kisslang.source.library.value.built_in.bool.LogicalValue;
import com.kisslang.source.library.Value;

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

public class ConditionalExpression implements Expression {

    private Expression expr1;
    private Expression expr2;
    private String operation;

    public ConditionalExpression(String operation,Expression expr1,Expression expr2){
        this.operation=operation;
        this.expr1=expr1;
        this.expr2=expr2;
    }

    @Override
    public Value eval() {

        Value result;

        switch (operation){

            case ">":
                result = new LogicalValue(expr1.eval().asNumber()>expr2.eval().asNumber());
                break;
            case ">=":
                result = new LogicalValue(expr1.eval().asNumber()>=expr2.eval().asNumber());
                break;
            case "<":
                result = new LogicalValue(expr1.eval().asNumber()<expr2.eval().asNumber());
                break;
            case "<=":
                result = new LogicalValue(expr1.eval().asNumber()<=expr2.eval().asNumber());
                break;
            case "==":
                result = new LogicalValue(expr1.eval().asNumber()==expr2.eval().asNumber());
                break;
            case "!=":
                result = new LogicalValue(expr1.eval().asNumber()!=expr2.eval().asNumber());
                break;
            default:
                result=new LogicalValue(false);
                break;
        }

        return result;
    }
}
