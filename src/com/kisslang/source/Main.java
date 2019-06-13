package com.kisslang.source;

import com.kisslang.source.compilation.KISSCompiler;
import com.kisslang.source.parser.Lexer;
import com.kisslang.source.parser.Parser;
import com.kisslang.source.parser.Token;
import com.kisslang.source.parser.ast.Statement;

import java.util.List;

public class Main {

    public static void main(String[] args) {

//        String input=" abc=( 2.43+ -2.43 ) *2 + PI^3 \n Print abc";

//        String input="ABC=25*3\nB=25+PI+ABC \nprint ABC";

        String input="a=25>3 \nPrint a>3 \nPrint a\na=5^(2*2) \nPrint a";

        KISSCompiler compiler=KISSCompiler.getInstance(input);

        System.out.println(compiler.getSourceCode());

        System.out.println();
        System.out.println();

        compiler.execute();


    }
}
