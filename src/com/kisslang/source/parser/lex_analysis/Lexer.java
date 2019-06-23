package com.kisslang.source.parser.lex_analysis;


import com.kisslang.source.parser.lex_analysis.tokenize_handlers.*;
import com.kisslang.source.parser.tokenization.Token;
import com.kisslang.source.parser.tokenization.TokenType;

import java.util.ArrayList;
import java.util.List;

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

public final class Lexer {

    private static final String OPERATOR_CHARS = "+-*/()=^<>&|!{};,[]->";

    private SourceCode sourceCode;

    public Lexer ( String input ) {
        sourceCode = SourceCode.getInstance ( );
        sourceCode.setCodeText ( input );
    }

    public List<Token> tokenize () {

        while (sourceCode.inBounds ( )) {

            final char current = sourceCode.peekCharacter ( 0 );

            TokenizeHandler handler;

            if ( Character.isDigit ( current ) )
                handler = new NormalNumberTokenizeHandler ( sourceCode );
            else if ( isValidWordStart ( current ) )
                handler = new WordTokenizeHandler ( sourceCode );
            else if ( current == '#' )
                handler = new HexNumberTokenizeHandler ( sourceCode );
            else if ( current == '"' || current == '\'' ) {
                sourceCode.nextCharacter ( );
                if ( current == '"' )
                    handler = new StringTokenizeHandler ( sourceCode , '"' );
                else
                    handler = new StringTokenizeHandler ( sourceCode , '\'' );
            }
            else if ( OPERATOR_CHARS.indexOf ( current ) != -1 )
                handler = new OperatorTokenizeHandler ( sourceCode );
            else {
                // whitespaces
                sourceCode.nextCharacter ( );
                continue;
            }

            handler.handle ( );
            handler.addToken ( );

        }

        return Tokens.getInstance ( ).getTokens ( );
    }

    private boolean isValidWordStart ( char c ) {
        return Character.isLetter ( c ) || c == '$';
    }

}