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

import com.kisslang.source.parser.tokenization.TokenType;

import java.util.HashMap;
import java.util.Map;

public final class OperatorsTable {

    private static Map<String, TokenType> OPERATORS;

    static {
        OPERATORS = new HashMap<> ( );
        OPERATORS.put ( "+" , TokenType.PLUS );
        OPERATORS.put ( "-" , TokenType.MINUS );
        OPERATORS.put ( "*" , TokenType.STAR );
        OPERATORS.put ( "/" , TokenType.SLASH );
        OPERATORS.put ( "(" , TokenType.LPAREN );
        OPERATORS.put ( "{" , TokenType.LPAREN_FIGURE );
        OPERATORS.put ( "[" , TokenType.LPAREN_SQUARE );
        OPERATORS.put ( "]" , TokenType.RPAREN_SQUARE );
        OPERATORS.put ( ")" , TokenType.RPAREN );
        OPERATORS.put ( "}" , TokenType.RPAREN_FIGURE );
        OPERATORS.put ( "^" , TokenType.POW );
        OPERATORS.put ( "<" , TokenType.LOWER_THAN );
        OPERATORS.put ( "<=" , TokenType.LOWER_OR_EQUAL_THAN );
        OPERATORS.put ( ">" , TokenType.GREATER_THAN );
        OPERATORS.put ( ">=" , TokenType.GREATER_OR_EQUAL_THAN );
        OPERATORS.put ( "==" , TokenType.EQUAL );
        OPERATORS.put ( "=" , TokenType.ASSIGN );
        OPERATORS.put ( "!" , TokenType.NOT );
        OPERATORS.put ( "&" , TokenType.AND );
        OPERATORS.put ( "&&" , TokenType.AND2 );
        OPERATORS.put ( "|" , TokenType.OR );
        OPERATORS.put ( "||" , TokenType.OR2 );
        OPERATORS.put ( ";" , TokenType.DELIMITER_FOR );
        OPERATORS.put ( "," , TokenType.DELIMITER_ARGS );
        OPERATORS.put ( "->" , TokenType.ARROW );
    }

    public static boolean isEmpty(){
        return OPERATORS.isEmpty ();
    }

    public static boolean hasKey(String key){
        return OPERATORS.containsKey ( key );
    }

    public static TokenType get (String key) {

        if ( hasKey ( key ) ){
            return OPERATORS.get ( key );
        }
        throw new RuntimeException ( "No such operator ..." );
    }
}
