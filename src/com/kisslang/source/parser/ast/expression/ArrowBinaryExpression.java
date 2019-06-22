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

    private final Expression expr2;

    public ArrowBinaryExpression(Expression expr1,Expression expr2){
        this.expr1=expr1;
        this.expr2=expr2;
    }

    @Override
    public Value eval() {

        VariableKey key;

        if( expr1 instanceof VariableExpression){

            key=new VariableKey (expr1.eval().asString (),false);
        }
        else if(expr1 instanceof ConstantExpression){
            key=new VariableKey (expr1.eval().asString (),true);
        }
        else{throw new RuntimeException ("Expected field...");}

        ObjectValue objectValue=(ObjectValue) Variables.get ( key );

        VariableKey key_object;

        if( expr2 instanceof VariableExpression){

            key_object=new VariableKey (expr2.eval().asString (),false);
        }
        else if(expr2 instanceof ConstantExpression){

            key_object=new VariableKey (expr2.eval().asString (),true);
        }
        else { throw new RuntimeException ("Expected field..."); }

        return objectValue.get (key_object);

    }

}
