package com.kisslang.source.library.value.built_in.string;

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

public class StringValue implements Value {

    private final String value;

    public StringValue(String value){
        this.value=value;
    }

    @Override
    public String asString() {
        return value;
    }

    @Override
    public double asNumber() {
        double result;
        try {
            result=Double.parseDouble(value);
        }
        catch (NumberFormatException e){
            result=Double.NaN;
        }
        return result;
    }

    @Override
    public boolean asBoolean() {
        if(value.equals("")){
            return false;
        }
        return true;
    }

    @Override
    public boolean canBeRepresentedAsNumber() {
        double result;
        try{
            result=Double.parseDouble(value);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return asString();
    }

    @Override
    public boolean isString() {
        return true;
    }
}
