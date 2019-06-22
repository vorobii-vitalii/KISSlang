package com.kisslang.source.parser.ast.helper;

import com.kisslang.source.library.value.built_in.bool.LogicalValue;
import com.kisslang.source.library.value.built_in.number.NumberValue;
import com.kisslang.source.library.value.built_in.string.StringValue;
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

public class InputRecognizer {

    private final String context;

    public InputRecognizer ( String context ) {
        this.context = context;
    }

    public Value recognize () {

        if ( ContextRecognize.isNumberLike ( context ) ) {
            return new NumberValue ( Double.parseDouble ( context ) );
        } else if ( ContextRecognize.isBooleanLike ( context ) ) {
            return new LogicalValue ( LogicalValue.toBoolean ( context ) );
        } else {
            return new StringValue ( context );
        }

    }

}
