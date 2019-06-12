package com.kisslang.source.library;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public final class Variables {

    private static Map<String,Value> variables;

    private static final Value NUMBER_VALUE_NOT_FOUND=new NumberValue(0);

    static {
        variables=new HashMap<String,Value>();
        variables.put("PI",new NumberValue(Math.PI));
        variables.put("E",new NumberValue(Math.E));
        variables.put("GOLDEN_RATIO",new NumberValue(1.618));
    }

    public static boolean isExists(String key){
        return variables.containsKey(key) ;
    }

    public static Value get(String key){
        if(!isExists(key)){
            return NUMBER_VALUE_NOT_FOUND;
        }
        return variables.get(key);
    }

    public static void add(String name,Value value){
        variables.put(name,value);
    }

}
