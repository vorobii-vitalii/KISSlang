package com.kisslang.source.library.value.built_in.bool;

import com.kisslang.source.library.Value;

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

public class LogicalValue implements Value {

    private final boolean value;

    public LogicalValue ( boolean value ) {
        this.value = value;
    }

    @Override
    public double asNumber () {

        if ( value == true ) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean asBoolean () {
        return value;
    }

    public static boolean toBoolean ( final String term ) {

        if ( term.equals ( "True" ) ) {
            return true;
        } else if ( term.equals ( "False" ) ) {
            return false;
        }
        throw new RuntimeException ( "Cannot cast " + term + " to Boolean..." );
    }

    public String representBooleanAsString ( boolean value ) { //Python style
        return Boolean.toString ( value ).replaceFirst ( Character.toString ( Boolean.toString ( value ).charAt ( 0 ) ) , Character.toString ( Character.toUpperCase ( Boolean.toString ( value ).charAt ( 0 ) ) ) );
    }

    @Override
    public String asString () {
        return representBooleanAsString ( value );
    }

    @Override
    public boolean canBeRepresentedAsNumber () {
        return true;
    }

    @Override
    public String toString () {
        return asString ( );
    }

    @Override
    public boolean isString () {
        return false;
    }
}
