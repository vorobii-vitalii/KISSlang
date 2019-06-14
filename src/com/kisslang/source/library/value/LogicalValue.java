package com.kisslang.source.library.value;

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
    public boolean asBoolean() {
        return value;
    }

    public String representBooleanAsString(boolean value){ //Python style
        return Boolean.toString(value).replaceFirst(Character.toString(Boolean.toString(value).charAt(0)),Character.toString(Character.toUpperCase(Boolean.toString(value).charAt(0))));
    }

    @Override
    public String asString() {
        return representBooleanAsString(value);
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
