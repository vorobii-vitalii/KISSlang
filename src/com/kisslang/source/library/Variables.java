package com.kisslang.source.library;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public final class Variables {

    private static Map<String,Double> variables;

    static {
        variables=new HashMap<String,Double>();
        variables.put("PI",Math.PI);
        variables.put("E",Math.E);
        variables.put("GOLDEN RATIO",1.618);
    }

    public static boolean isExists(String key){
        return variables.containsKey(key) ;
    }

    public static double get(String key){
        if(!isExists(key)){
            return 0;
        }
        return variables.get(key);
    }

    public static void add(String name,double value){
        variables.put(name,value);
    }

}
