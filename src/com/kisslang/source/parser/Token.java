package com.kisslang.source.parser;

public final class Token {

    private String text;

    private TokenType type;

    public Token(){

    }

    public Token(TokenType type,String text){
        this.type=type;
        this.text=text;
    }

    public Token(TokenType type){
        this.type=type;
        this.text="";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return type;
    }

    @Override
    public String toString(){
        return "Token: "+type+" "+text;
    }
}
