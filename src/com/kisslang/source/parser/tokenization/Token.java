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

public final class Token {

    private String text;

    private TokenType type;

    public Token () {
        throw new RuntimeException ( "TokenType expected..." );
    }

    public Token ( TokenType type , String text ) {
        this.type = type;
        this.text = text;
    }

    public Token ( TokenType type ) {
        this.type = type;
        this.text = "";
    }

    public String getText () {
        return text;
    }

    public void setText ( String text ) {
        this.text = text;
    }

    public void setType ( TokenType type ) {
        this.type = type;
    }

    public TokenType getType () {
        return type;
    }

    @Override
    public String toString () {
        return "Token: " + type + " " + text;
    }

}
