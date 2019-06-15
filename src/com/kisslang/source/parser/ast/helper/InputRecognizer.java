package com.kisslang.source.parser.ast.helper;

import com.kisslang.source.library.value.built_in.bool.LogicalValue;
import com.kisslang.source.library.value.built_in.number.NumberValue;
import com.kisslang.source.library.value.built_in.string.StringValue;
import com.kisslang.source.library.value.built_in.Value;

public class InputRecognizer {

    private final String context;

    public InputRecognizer(String context){
        this.context=context;
    }

    public Value recognize(){

        if ( ContextRecognize.isNumberLike(context) ) {
            return new NumberValue(Double.parseDouble(context));
        }
        else if(ContextRecognize.isBooleanLike(context)) {
            return new LogicalValue(LogicalValue.toBoolean(context));
        }
        else {
            return new StringValue(context);
        }

    }

}
