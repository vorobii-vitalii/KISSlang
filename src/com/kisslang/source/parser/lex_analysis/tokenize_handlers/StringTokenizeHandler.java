package com.kisslang.source.parser.lex_analysis.tokenize_handlers;

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

import com.kisslang.source.parser.lex_analysis.SourceCode;
import com.kisslang.source.parser.lex_analysis.TokenizeHandler;
import com.kisslang.source.parser.tokenization.Token;
import com.kisslang.source.parser.tokenization.TokenType;

public final class StringTokenizeHandler extends TokenizeHandler {

    private char strEscape;

    private SourceCode sourceCode;

    public StringTokenizeHandler ( SourceCode sourceCode , char strEscape ) {
        this.sourceCode = sourceCode;
        this.strEscape = strEscape;
    }

    @Override
    public void handle () {
        final StringBuilder buffer = new StringBuilder ( );
        char current = sourceCode.peekCharacter ( 0 );

        while (current != strEscape) {

            if (current=='\\' && sourceCode.peekCharacter ( 1 )=='n'){
                sourceCode.nextCharacter ();
                buffer.append ( "\n" );
            }
            else if (current=='\\' && sourceCode.peekCharacter ( 1 )=='t'){
                sourceCode.nextCharacter ();
                buffer.append ( "\t" );
            }
            else if (current=='\\' && sourceCode.peekCharacter ( 1 )=='r'){
                sourceCode.nextCharacter ();
                buffer.append ( "\r" );
            }
            else {
                buffer.append ( current );
            }
            current = sourceCode.nextCharacter ( );

        }

        sourceCode.nextCharacter ( );
        this.token = new Token ( TokenType.STRING_TEXT , buffer.toString ( ) );
    }
}
