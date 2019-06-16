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

public class FunctionKey {

    private String functionName;

    private int argCount;

    private boolean immutable;

    public FunctionKey(String functionName, int argCount,boolean immutable){
        this.functionName = functionName;
        this.argCount=argCount;
        this.immutable=immutable;
    }

    public FunctionKey(){
        throw new RuntimeException("Expected function name ... ");
    }

    public FunctionKey(String functionName){
        throw new RuntimeException("Expected number of arguments");
    }

    public String getFunctionName(){
        return functionName;
    }

    public boolean isImmutable(){
        return immutable;
    }

    @Override
    public int hashCode() {
        return functionName.hashCode()+ argCount+ ( immutable ? 1:0 );
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode()==this.hashCode();
    }
}
