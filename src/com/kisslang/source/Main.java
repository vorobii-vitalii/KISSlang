package com.kisslang.source;

import com.kisslang.source.parser.Lexer;
import com.kisslang.source.parser.Token;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String input="214+278-32*4";

        Lexer obj=new Lexer(input);

        List<Token> tokens=obj.tokenize();

        System.out.println();
        for (Token token:tokens) {
            System.out.println(token+" ");
        }
        System.out.println();


    }
}
