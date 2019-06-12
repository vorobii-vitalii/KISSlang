package com.kisslang.source.parser;


import java.util.ArrayList;
import java.util.List;

public final class Lexer {

    private String input;

    private static final String OPERATOR_CHARS="+-*/";

    private static final TokenType [] OPERATOR_TOKENS={
        TokenType.PLUS,TokenType.MINUS,TokenType.STAR,TokenType.SLASH
    };

    private final List<Token> tokens;

    private int pos;

    private final int length;

    public Lexer(String input){
        this.input=input;
        this.length=input.length();
        tokens=new ArrayList<>();
    }

    private char peek(int relPos){
        final int position=pos+relPos;

        if (position>=length){
            return '\0';
        }
        return input.charAt(position);
    }

    private char next(){
        pos++;
        return peek(0);
    }

    public List<Token> tokenize(){

        while(pos<length){

            final char current=peek(0);

            if (Character.isDigit(current)){
                tokenizeNumber();
            }
            else if(OPERATOR_CHARS.indexOf(current)!=-1){
                tokenizeOperator();
            }
            else{
                next();
            }

        }

        return tokens;
    }

    private void tokenizeOperator() {
        final int position=OPERATOR_CHARS.indexOf(peek(0));
        tokens.add(new Token(OPERATOR_TOKENS[position]));
        next();
    }

    private void tokenizeNumber() {
        char current=peek(0);
        final StringBuilder buffer=new StringBuilder();
        while (Character.isDigit(current)){
            buffer.append(current);
            current=next();
        }
        addToken(TokenType.NUMBER,buffer.toString());
    }

    private void addToken(TokenType type){
        tokens.add(new Token(type));
    }

    private void addToken(TokenType type,String text){
        tokens.add(new Token(type,text));
    }



}
