package com.kisslang.source.parser;


import java.util.ArrayList;
import java.util.List;

public final class Lexer {

    private static final String OPERATOR_CHARS = "+-*/()=";
    private static final TokenType[] OPERATOR_TOKENS = {
            TokenType.PLUS, TokenType.MINUS,
            TokenType.STAR, TokenType.SLASH,
            TokenType.LPAREN, TokenType.RPAREN,
            TokenType.ASSIGN
    };

    private final String input;
    private final int length;

    private final List<Token> tokens;

    private int pos;

    public Lexer(String input) {
        this.input = input;
        length = input.length();

        tokens = new ArrayList<>();
    }

    public List<Token> tokenize() {
        while (pos < length) {
            final char current = peek(0);
            if (Character.isDigit(current)) tokenizeNumber();
            else if(Character.isLetter(current)) tokenizeWord();
            else if (current == '#') {
                next();
                tokenizeHexNumber();
            }
            else if(current=='"' || current=='\''){
                next();
                if(current=='"'){
                    tokenizeStringValue('"');
                }
                else{
                    tokenizeStringValue('\'');
                }

            }
            else if (OPERATOR_CHARS.indexOf(current) != -1) {
                tokenizeOperator();
            } else {
                // whitespaces
                next();
            }
        }
        return tokens;
    }

    private void tokenizeStringValue(char c) {
        final StringBuilder buffer=new StringBuilder();
        char current=peek(0);
        while(current!=c){
            buffer.append(current);
            current=next();
        }
        next();
        addToken(TokenType.STRING_TEXT,buffer.toString());
    }

    private void tokenizeWord() {
        final StringBuilder buffer=new StringBuilder();
        char current=peek(0);
        while (Character.isLetterOrDigit(current) || current=='_' || current=='$'){
            buffer.append(current);
            current=next();
        }
        if (buffer.toString().equals("print")){
            addToken(TokenType.PRINT);
            return;
        }
        addToken(TokenType.WORD,buffer.toString());
    }

    private void tokenizeNumber() {
        final StringBuilder buffer = new StringBuilder();
        char current = peek(0);
        while (Character.isDigit(current) || current=='.') {
            if (current=='.'){
                if (buffer.indexOf(".")!=-1){
                    throw new RuntimeException("Invalid number declaration");
                }
            }
            buffer.append(current);
            current = next();
        }

        addToken(TokenType.NUMBER, buffer.toString());
    }

    private void tokenizeHexNumber() {
        final StringBuilder buffer = new StringBuilder();
        char current = peek(0);
        while (Character.isDigit(current) || isHexNumber(current)) {
            buffer.append(current);
            current = next();
        }
        addToken(TokenType.HEX_NUMBER, buffer.toString());
    }

    private static boolean isHexNumber(char current) {
        return "abcdef".indexOf(Character.toLowerCase(current)) != -1;
    }

    private void tokenizeOperator() {
        final int position = OPERATOR_CHARS.indexOf(peek(0));
        addToken(OPERATOR_TOKENS[position]);
        next();
    }

    private char next() {
        pos++;
        return peek(0);
    }

    private char peek(int relativePosition) {
        final int position = pos + relativePosition;
        if (position >= length) return '\0';
        return input.charAt(position);
    }

    private void addToken(TokenType type) {
        addToken(type, "");
    }

    private void addToken(TokenType type, String text) {
        tokens.add(new Token(type, text));
    }
}