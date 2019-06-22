package com.kisslang.source.library.value.built_in.object;

import com.kisslang.source.library.Value;
import com.kisslang.source.library.keys.VariableKey;
import com.kisslang.source.library.value.built_in.bool.LogicalValue;

import java.util.HashMap;
import java.util.Map;

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

public class ObjectValue implements Value {

    private final Map<VariableKey, Value> table;

    public ObjectValue ( Map<VariableKey, Value> table ) {
        this.table = table;
    }

    public Value get ( VariableKey key ) {

        if ( table.containsKey ( key ) ) {
            return table.get ( key );
        }

        return new LogicalValue ( false );
    }

    public void add ( VariableKey key , Value value ) {

        if ( key.isImmutable ( ) && table.containsKey ( key ) ) {
            throw new RuntimeException ( "Const key to that object was already provided" );
        }

        table.put ( key , value );
    }

    private boolean isEmpty () {
        return table.isEmpty ( );
    }


    @Override
    public double asNumber () {
        return 0;
    }

    @Override
    public String asString () {
        StringBuilder buffer = new StringBuilder ( );
        for (VariableKey key1 : table.keySet ( )) {
            buffer.append ( key1.getVariableName ( ) + " -> " + table.get ( key1 ) );
            buffer.append ( "\n" );
        }
        return buffer.toString ( );
    }

    @Override
    public String toString () {
        return asString ( );
    }

    @Override
    public boolean canBeRepresentedAsNumber () {
        return false;
    }

    @Override
    public boolean asBoolean () {

        return !isEmpty ( );
    }
}
