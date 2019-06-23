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

public final class SourceCode {

    private static volatile SourceCode singletonObject;

    private String codeText;

    private int codeLength;

    private int currentCharacterIndex;

    static SourceCode getInstance (){

        if(singletonObject==null){
            singletonObject = new SourceCode ();
        }

        return singletonObject;
    }

    boolean inBounds (){
        return currentCharacterIndex<codeLength;
    }

    void setCodeText ( String codeText ){
        this.codeText = codeText;
        this.codeLength=codeText.length ();
        this.currentCharacterIndex=0;
    }

    public char peekCharacter ( int relativePosition ) {
        final int position = currentCharacterIndex + relativePosition;
        if ( position >= codeLength ) return '\0';
        return codeText.charAt ( position );
    }

    public char nextCharacter ( ) {
        currentCharacterIndex++;
        return peekCharacter ( 0 );
    }

    private SourceCode(){

    }

}
