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

/**
 * Provides normal number tokenization
 */
public final class NormalNumberTokenizeHandler extends TokenizeHandler {

    private SourceCode sourceCode;

    public NormalNumberTokenizeHandler ( SourceCode sourceCode ) {
        this.sourceCode = sourceCode;
    }

    /**
     * Appends to buffer characters till character isDigit() or '.' (can happen only once)
     */
    @Override
    public void handle () {

        final StringBuilder buffer = new StringBuilder ( );

        char current = sourceCode.peekCharacter ( 0 );

        while (Character.isDigit ( current ) || current == '.') {
            if ( current == '.' ) {
                if ( buffer.indexOf ( "." ) != -1 ) {
                    throw new RuntimeException ( "Invalid number declaration" );
                }
            }
            buffer.append ( current );
            current = sourceCode.nextCharacter ( );
        }

        this.token = new Token ( TokenType.NUMBER , buffer.toString ( ) );
    }
}
