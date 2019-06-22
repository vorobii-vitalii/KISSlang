package com.kisslang.source.parser.ast.helper;

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

public final class ContextRecognize {

    private final static boolean isNumberSeperator ( char c ) {

        return c == '.';
    }

    public final static boolean isNumberLike ( final String term ) {

        final int lastIndex = term.length ( ) - 1;

        if ( !Character.isDigit ( term.charAt ( 0 ) ) ) {
            return false;
        }

        for (int i = 1; i < lastIndex; i++) {

            if ( !Character.isDigit ( term.charAt ( i ) ) ) {

                if ( !isNumberSeperator ( term.charAt ( i ) ) ) {
                    return false;
                }
            }
        }

        return Character.isDigit ( term.charAt ( lastIndex ) );
    }

    public final static boolean isBooleanLike ( final String term ) {

        return term.equals ( "True" ) || term.equals ( "False" );

    }


}
