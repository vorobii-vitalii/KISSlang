package com.kisslang.source.library;

public class LogicalValue implements Value {

    private final boolean value;

    public LogicalValue(boolean value){
        this.value=value;
    }

    @Override
    public double asDouble() {

        if(value==true){
            return 1;
        }
        return 0;
    }

    @Override
    public String asString() {
        return Boolean.toString(value).replaceFirst(Character.toString(Boolean.toString(value).charAt(0)),Character.toString(Character.toUpperCase(Boolean.toString(value).charAt(0))));
    }

    @Override
    public boolean canBeRepresentedAsNumber() {
        return false;
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
