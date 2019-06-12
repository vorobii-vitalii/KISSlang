package com.kisslang.source.library;

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
        return Double.parseDouble(value);
    }
}
