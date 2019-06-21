package com.kisslang.source.parser.tokenization;

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

public enum TokenType {

    AND,
    AND2,
    ASSIGN,
    BREAK,
    CONTINUE,
    DELIMITER_ARGS,
    DELIMITER_FOR,
    ELSE,
    EOF,
    EQUAL,
    FOR,
    FUNCTION_DECLARATION,
    GREATER_OR_EQUAL_THAN,
    GREATER_THAN,
    HEX_NUMBER,
    IF,
    IMMUTABLE_NAME,
    INPUT,
    LOWER_OR_EQUAL_THAN,
    LOWER_THAN,
    LPAREN, // (
    LPAREN_FIGURE,
    LPAREN_SQUARE,
    MINUS,
    MUTTABLE_NAME,
    NOT,
    NUMBER,
    OR,
    OR2,
    PLUS,
    POW,
    PRINT,
    PRINTLINE,
    RETURN_FROM_METHOD,
    RPAREN, // )
    RPAREN_FIGURE,
    RPAREN_SQUARE,
    SLASH,
    STAR,
    STRING_TEXT,
    WHILE


}