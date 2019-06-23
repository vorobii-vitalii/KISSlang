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

/**
 * Source code representation in object.
 * Also provides some utilities to encapsulate string processing
 */
public final class SourceCode {

    private static volatile SourceCode singletonObject;

    private String codeText;

    private int codeLength;

    private int currentCharacterIndex;

    static SourceCode getInstance () {

        if ( singletonObject == null ) {
            singletonObject = new SourceCode ( );
        }

        return singletonObject;
    }

    /**
     * @return boolean accordingly current character index is lower than source code length or not
     */
    boolean inBounds () {
        return currentCharacterIndex < codeLength;
    }

    /**
     * @param codeText the source code
     */
    void setCodeText ( String codeText ) {
        this.codeText = codeText;
        this.codeLength = codeText.length ( );
        this.currentCharacterIndex = 0;
    }

    /**
     * @param relativePosition
     * @return symbol in code with position relative to current on relativePosition arg or '\0' if out of bounds
     */
    public char peekCharacter ( int relativePosition ) {
        final int position = currentCharacterIndex + relativePosition;
        if ( position >= codeLength ) return '\0';
        return codeText.charAt ( position );
    }

    /**
     * Moves to next character in source code
     * @return next symbol
     */
    public char nextCharacter () {
        currentCharacterIndex++;
        return peekCharacter ( 0 );
    }

    private SourceCode () {

    }

}