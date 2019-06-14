package com.kisslang.source.library.value;

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
    public double asDouble() {
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