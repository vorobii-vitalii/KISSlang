package com.kisslang.source.library.value;

public interface Value {

    double asDouble();

    String asString();

    boolean canBeRepresentedAsNumber();

    boolean isString();

    boolean asBoolean();

}