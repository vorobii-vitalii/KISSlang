package com.kisslang.source;

import com.kisslang.source.parser.Lexer;
import com.kisslang.source.parser.Parser;
import com.kisslang.source.parser.Token;
import com.kisslang.source.parser.ast.Expression;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan=new Scanner(System.in);

        String input="(2+2)*2";

        Lexer obj=new Lexer(input);

        List<Token> tokens=obj.tokenize();

        System.out.println();
        for (Token token:tokens) {
            System.out.println(token+" ");
        }
        System.out.println();

        final List<Expression> expressions=new Parser(tokens).parse();

        for (Expression e:
             expressions) {
            System.out.println(e+" "+e.eval());

        }


    }
}
