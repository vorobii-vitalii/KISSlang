package com.kisslang.source.parser.ast.statements.functional;

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

public class Argument {

    private String name;

    private boolean immutable;

    public Argument(String name,boolean immutable){
        this.name=name;
        this.immutable=immutable;
    }

    public String getArgumentName() {
        return name;
    }

    public boolean isImmutable() {
        return immutable;
    }

    @Override
    public int hashCode() {
        return name.hashCode()+(immutable ? 1:0 );
    }

    @Override
    public boolean equals(Object o) {
        return this.hashCode()==o.hashCode();
    }
}
