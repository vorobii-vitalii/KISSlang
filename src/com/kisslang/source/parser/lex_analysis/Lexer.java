package com.kisslang.source.parser.lex_analysis;


import com.kisslang.source.parser.lex_analysis.tokenize_handlers.*;
import com.kisslang.source.parser.tokenization.Token;

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

/**
 * Provides lexical analysis of source code
 */
public final class Lexer {

    private static final String OPERATOR_CHARS = "+-*/()=^<>&|!{};,[]->";

    private SourceCode sourceCode;

    /**
     * Takes the input string as the source code and immediately initalizes SourceCode Singleton Object
     * @see SourceCode
     * @param input the actual source code
     */
    public Lexer ( String input ) {
        sourceCode = SourceCode.getInstance ( );
        sourceCode.setCodeText ( input );
    }

    /**
     * While the <b>current</b> position of character is in bounds of source code
     * we literary check the context of each character
     * And call appropriate <i>handle()</i> method , if token is not <i>empty</i>
     * After save token in List
     * @see SourceCode
     * @see TokenizeHandler
     * @see NormalNumberTokenizeHandler
     * @see WordTokenizeHandler
     * @see HexNumberTokenizeHandler
     * @see StringTokenizeHandler
     * @see OperatorTokenizeHandler
     * @see Tokens
     * @return The list of Tokens parsed
     */
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

    /**
     * Every variable or constant in KISSlang should start either from dollar sign or letter
     * @see Character
     * @param c the character
     * @return boolean value accordingly, the symbol corresponds to the condition
     */
    private boolean isValidWordStart ( char c ) {
        return Character.isLetter ( c ) || c == '$';
    }

}