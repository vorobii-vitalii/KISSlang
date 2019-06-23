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
import com.kisslang.source.parser.lex_analysis.WordTable;

/**
 * Performs words tokenization
 */
public final class WordTokenizeHandler extends TokenizeHandler {

    private SourceCode sourceCode;

    public WordTokenizeHandler ( SourceCode sourceCode ) {
        this.sourceCode = sourceCode;
    }

    /**
     * Reads characters to buffer till character is valid in word
     * Can consists letter or digit, '_' and dollar sign '$'
     */
    @Override
    public void handle () {

        final StringBuilder buffer = new StringBuilder ( );

        char current = sourceCode.peekCharacter ( 0 );

        while (isValidCharacter ( current )) {
            buffer.append ( current );
            current = sourceCode.nextCharacter ( );
        }

        this.token = WordTable.get ( buffer.toString ( ) );
    }

    private boolean isValidCharacter ( char c ) {
        return Character.isLetterOrDigit ( c ) || c == '_' || c == '$';
    }

}
