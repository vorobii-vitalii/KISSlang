package com.kisslang.source.library;

public class NumberValue implements Value {

    private final double value;

    public NumberValue(double value){
        this.value=value;
    }

    @Override
    public double asDouble() {
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
