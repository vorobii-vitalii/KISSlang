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

import com.kisslang.source.parser.lex_analysis.OperatorsTable;
import com.kisslang.source.parser.lex_analysis.SourceCode;
import com.kisslang.source.parser.lex_analysis.TokenizeHandler;
import com.kisslang.source.parser.tokenization.Token;

public final class OperatorTokenizeHandler extends TokenizeHandler {

    private SourceCode sourceCode;

    public OperatorTokenizeHandler(SourceCode sourceCode){
        this.sourceCode=sourceCode;
    }

    private void tokenizeSingleLineCommentary () {
        char current = sourceCode.peekCharacter ( 0 );

        while ("\r\n\0".indexOf ( current ) == -1) {
            current = sourceCode.nextCharacter ();
        }

    }

    @Override
    public void handle () {
        char current = sourceCode.peekCharacter ( 0 );
        if ( current == '/' ) {
            if ( sourceCode.peekCharacter ( 1 ) == '/' ) {
                sourceCode.nextCharacter ();
                sourceCode.nextCharacter ();
                tokenizeSingleLineCommentary ( );
                return;
            }
        }
        StringBuffer buffer = new StringBuffer ( );
        while (true) {
            String curStr = buffer.toString ( );
            if ( !OperatorsTable.hasKey ( curStr + current ) && !curStr.isEmpty ( ) ) {
                this.token=  new Token ( OperatorsTable.get ( curStr ) );
                return;
            }
            buffer.append ( current );
            current = sourceCode.nextCharacter ();

        }
    }

}
