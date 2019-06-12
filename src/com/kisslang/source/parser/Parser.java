package com.kisslang.source.parser;

import com.kisslang.source.parser.ast.BinaryExpression;
import com.kisslang.source.parser.ast.Expression;
import com.kisslang.source.parser.ast.NumberExpression;

import java.util.List;

public final class Parser {

    private List<Token> tokens;

    private final int size;

    private int pos;

    public Parser(List<Token> tokens){
        this.tokens=tokens;
        size=tokens.size();
    }

    public List<Expression> parse(){
        return null;
    }

    private Expression expression(){
        return
    }

    private Expression additive(){
        Expression expr=multiplicative();

        while (true){
            //2 * 6 /3
            if (match(TokenType.PLUS)){
                expr= new BinaryExpression('+',expr,unary());
                continue;
            }
            else if (match(TokenType.MINUS)){
                expr= new BinaryExpression('-',expr,unary());
                continue;
            }
            break;
        }
        return multiplicative();
    }

    private Expression multiplicative(){
        Expression expr=unary();

        while (true){
            //2 * 6 /3
            if (match(TokenType.STAR)){
                expr= new BinaryExpression('*',expr,unary());
                continue;
            }
            else if (match(TokenType.SLASH)){
                expr= new BinaryExpression('/',expr,unary());
                continue;
            }
            break;
        }
        return unary();
    }

    private Expression unary(){

        return primary();
    }

    private Expression primary(){
        final Token current=get(0);
        if (match(TokenType.NUMBER)){
            return new NumberExpression(Double.parseDouble(current.getText()))
        }
        return unknown();
    }

    private Expression unknown(){
        throw new RuntimeException("Unknown expression");
        return null;
    }

    private Token get(int relPos){
        final int position=pos+relPos;
        if (position>=size){
            return new Token(TokenType.EOF);
        }
        return tokens.get(position);
    }

    private boolean match(TokenType type){
        final Token current=get(0);
        if (type!=current.getType()){
            return false;
        }
        pos++;
        return true;
    }
}
