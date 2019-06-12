package com.kisslang.source.library;

public interface Value {

    double asDouble();

    String asString();

    boolean canBeRepresentedAsNumber();
}