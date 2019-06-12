package com.kisslang.source;

import com.kisslang.source.parser.Lexer;
import com.kisslang.source.parser.Parser;
import com.kisslang.source.parser.Token;
import com.kisslang.source.parser.ast.Expression;
import com.kisslang.source.parser.ast.Statement;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        String input=" ( 2.43+ -2.43 ) *2 + PI";

//        String input="ABC=25*3\nB=25+PI+ABC \nprint ABC";
        String input="print \"string value\"";

        System.out.println(input);

        Lexer obj=new Lexer(input);

        List<Token> tokens=obj.tokenize();

        System.out.println();
        for (Token token:tokens) {
            System.out.println(token+" ");
        }
        System.out.println();

        final List<Statement> expressions=new Parser(tokens).parse();

        for (Statement e:
             expressions) {
//            System.out.print(e);
            e.execute();

        }


    }
}
