package com.kisslang.source.library;

import com.kisslang.source.library.keys.VariableKey;
import com.kisslang.source.library.value.built_in.number.NumberValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public final class Variables {

    private static Map<VariableKey, Value> variables;

    private static Stack<Map<VariableKey,Value>> stack;

    private static final Value NUMBER_VALUE_NOT_FOUND=new NumberValue(0);

    static {
        stack=new Stack<>();

        variables=new HashMap<>();
        variables.put(new VariableKey("PI",true),new NumberValue(Math.PI));
        variables.put(new VariableKey("E",true),new NumberValue(Math.E));
        variables.put(new VariableKey("GOLDEN_RATION",true),new NumberValue(1.618));
    }

    public static boolean isExists(VariableKey key){
            return variables.containsKey(key);
    }

    public static void push(){
        stack.push(new HashMap<>(variables));
    }

    public static void pop(){
        variables=stack.pop();
    }

    public static Value  get(VariableKey key){

            if (!isExists(key)) {
                return NUMBER_VALUE_NOT_FOUND;
            }

            return variables.get(key);
    }

    public static void add(VariableKey key,Value value){

            if(key.isImmutable()==true && isExists(key)){
                throw new RuntimeException("Constans are immutable ! ");
            }

            variables.put(key, value);
    }

}
