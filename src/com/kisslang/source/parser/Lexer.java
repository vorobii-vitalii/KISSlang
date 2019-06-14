package com.kisslang.source.parser;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Lexer {

    private static final String OPERATOR_CHARS = "+-*/()=^<>&|!{};";
    
    private static Map <String,TokenType> OPERATORS;

    static {
        OPERATORS=new HashMap<>();
        OPERATORS.put("+",TokenType.PLUS);
        OPERATORS.put("-",TokenType.MINUS);
        OPERATORS.put("*",TokenType.STAR);
        OPERATORS.put("/",TokenType.SLASH);
        OPERATORS.put("(",TokenType.LPAREN);
        OPERATORS.put("{",TokenType.LPAREN_FIGURE);
        OPERATORS.put(")",TokenType.RPAREN);
        OPERATORS.put("}",TokenType.RPAREN_FIGURE);
        OPERATORS.put("^",TokenType.POW);
        OPERATORS.put("<",TokenType.LOWER_THAN);
        OPERATORS.put("<=",TokenType.LOWER_OR_EQUAL_THAN);
        OPERATORS.put(">",TokenType.GREATER_THAN);
        OPERATORS.put(">=",TokenType.GREATER_OR_EQUAL_THAN);
        OPERATORS.put("==",TokenType.EQUAL);
        OPERATORS.put("=",TokenType.ASSIGN);
        OPERATORS.put("!",TokenType.NOT);
        OPERATORS.put("&",TokenType.AND);
        OPERATORS.put("&&",TokenType.AND2);
        OPERATORS.put("|",TokenType.OR);
        OPERATORS.put("||",TokenType.OR2);
        OPERATORS.put(";",TokenType.DELIMITER_FOR);
}

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
        if (buffer.toString().equals("Print")){
            addToken(TokenType.PRINT);
            return;
        }
        if (buffer.toString().equals("If")){
            addToken(TokenType.IF);
            return;
        }
        if (buffer.toString().equals("Else")){
            addToken(TokenType.ELSE);
            return;
        }
        if (buffer.toString().equals("While")){
            addToken(TokenType.WHILE);
            return;
        }
        if(buffer.toString().equals("For")){
            addToken(TokenType.FOR);
        }
        if(buffer.toString().equals("Continue")){
            addToken(TokenType.CONTINUE);
        }
        if(buffer.toString().equals("Break")){
            addToken(TokenType.BREAK);
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
       char current=peek(0);
       if (current=='/'){
           if(peek(1)=='/'){
               //System.out.println("Found");
               next();
               next();
               tokenizeSingleLineCommentary();
               return;
           }
           else if(peek(1)=='*'){
               next();
               next();
               tokenizeMultiLineCommentary();
               return;

           }
       }
       StringBuffer buffer=new StringBuffer();
       while(true){
           String curStr=buffer.toString();
           if(!OPERATORS.containsKey(curStr+current) && !curStr.isEmpty()){
               addToken(OPERATORS.get(curStr));
               return;
           }
           buffer.append(current);
           current=next();

       }
    }

    private void tokenizeMultiLineCommentary() {

        while(peek(0)!='*' && peek(1)!='/'){

            if(peek(0)=='\0'){
                throw new RuntimeException("Not found closing */ comment declaration");
            }

            next();
        }

        next();
        next();
    }

    private void tokenizeSingleLineCommentary() {
        char current=peek(0);

        while ("\r\n\0".indexOf(current)==-1){
            current=next();
        }

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