package com.kisslang.source.library.keys;

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

public final class VariableKey {

    private String varName;

    private boolean immutable;

    public VariableKey ( String varName , boolean immutable ) {
        this.varName = varName;
        this.immutable = immutable;
    }

    public VariableKey ( String varName ) {
        this.varName = varName;
        this.immutable = true;
    }

    public VariableKey () {
        throw new RuntimeException ( "Expected variable name..." );
    }

    public String getVariableName () {
        return varName;
    }

    public boolean isImmutable () {
        return immutable;
    }

    @Override
    public int hashCode () {
        return varName.hashCode ( ) + (immutable ? 1 : 0);
    }

    @Override
    public boolean equals ( Object o ) {
        return o.hashCode ( ) == this.hashCode ( );
    }
}
