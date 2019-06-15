package com.kisslang.source.parser.ast.expression;

import com.kisslang.source.library.value.built_in.Value;

public interface Expression {

    Value eval();

}
