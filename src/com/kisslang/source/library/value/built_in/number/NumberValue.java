package com.kisslang.source.library.value.built_in.number;

import com.kisslang.source.library.Value;

import java.math.BigDecimal;

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

public class NumberValue implements Value {

    private final double value;

    public NumberValue(double value){
        this.value=value;
    }

    @Override
    public double asNumber() {
        return value;
    }

    @Override
    public boolean asBoolean() {
        if(value!=0){
            return true;
        }
        return false;
    }

    @Override
    public String asString() {
        double doubleNumber = value;
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(doubleNumber));
        int intValue = bigDecimal.intValue();
        if(bigDecimal.subtract(new BigDecimal(intValue)).toPlainString().equals("0.0")){
            return Integer.toString( intValue);
        }
        return Double.toString(value);
    }

    @Override
    public boolean canBeRepresentedAsNumber() {
        return true;
    }

    @Override
    public String toString() {
        return asString();
    }

    @Override
    public boolean isString() {
        return false;
    }
}
