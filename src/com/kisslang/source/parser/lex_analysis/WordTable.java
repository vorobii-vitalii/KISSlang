package com.kisslang.source.parser.lex_analysis;

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

import com.kisslang.source.parser.tokenization.Token;
import com.kisslang.source.parser.tokenization.TokenType;

import java.util.HashMap;
import java.util.Map;

/**
 * Key-value pair of appropriate keywords and literals and its TokenType
 * @see TokenType
 * @see Map
 */
public final class WordTable {

    private static Map<String, TokenType> KEYWORDS;

    static {
        KEYWORDS = new HashMap<> ( );
        KEYWORDS.put ( "Print" , TokenType.PRINT );
        KEYWORDS.put ( "PrintLine" , TokenType.PRINTLINE );
        KEYWORDS.put ( "True" , TokenType.TRUE_LITERAL );
        KEYWORDS.put ( "False" , TokenType.FALSE_LITERAL );
        KEYWORDS.put ( "If" , TokenType.IF );
        KEYWORDS.put ( "Else" , TokenType.ELSE );
        KEYWORDS.put ( "Do" , TokenType.DO_LOOP );
        KEYWORDS.put ( "While" , TokenType.WHILE );
        KEYWORDS.put ( "For" , TokenType.FOR );
        KEYWORDS.put ( "Input" , TokenType.INPUT );
        KEYWORDS.put ( "Return" , TokenType.RETURN_FROM_METHOD );
        KEYWORDS.put ( "Continue" , TokenType.CONTINUE );
        KEYWORDS.put ( "Break" , TokenType.BREAK );
        KEYWORDS.put ( "Function" , TokenType.FUNCTION_DECLARATION );
    }

    private static boolean hasKey ( String key ) {
        return KEYWORDS.containsKey ( key );
    }

    /**
     * Method that takes the string key(string representation of keyword or variable or constant) and
     * accordingly whether
     * such keyword exists returns Token of keyword or Token of either constant or variable
     * Variable names start with dollar sign while constants names start with letter
     * @param key
     * @see TokenType
     * @see RuntimeException
     * @return TokenType of operator
     */
    public static Token get ( String key ) {

        if ( hasKey ( key ) ) {
            return new Token ( KEYWORDS.get ( key ) );
        } else if ( key.startsWith ( "$" ) ) {
            return new Token ( TokenType.MUTTABLE_NAME , key.substring ( 1 ) );
        } else {
            return new Token ( TokenType.IMMUTABLE_NAME , key );
        }
    }

}
