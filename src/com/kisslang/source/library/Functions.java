package com.kisslang.source.library;

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

import com.kisslang.source.library.keys.FunctionKey;
import com.kisslang.source.library.value.built_in.number.NumberValue;

import java.util.HashMap;
import java.util.Map;

public class Functions {

    private static Map<FunctionKey, Function> functions;

    static {

        functions=new HashMap<>();

        functions.put(new FunctionKey("sin",1), args -> {
                return new NumberValue(Math.sin(args[0].asNumber()));
        });

    }

    public static boolean isExists(FunctionKey key){
        return functions.containsKey(key);
    }

    public static Function  get(FunctionKey key){

        if (!isExists(key)) {
            return null;
        }

        return functions.get(key);
    }

    public static void add(FunctionKey key,Function value){

        if(isExists(key)){
            throw new RuntimeException("Cannot declare function with the same name && arguments count ! ");
        }

        functions.put(key, value);
    }


}
