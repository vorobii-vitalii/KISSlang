package com.kisslang.source.parser;

import com.kisslang.source.parser.ast.expression.*;
import com.kisslang.source.parser.ast.expression.binary.ArithmeticBinaryExpression;
import com.kisslang.source.parser.ast.expression.binary.LogicalBinaryExpression;
import com.kisslang.source.parser.ast.expression.unary.LogicalUnaryExpression;
import com.kisslang.source.parser.ast.expression.unary.UnaryExpression;
import com.kisslang.source.parser.ast.statements.*;
import com.kisslang.source.parser.ast.statements.condition.IfConditionalStatement;
import com.kisslang.source.parser.ast.statements.loop.BreakLoopStatement;
import com.kisslang.source.parser.ast.statements.loop.ContinueLoopStatement;
import com.kisslang.source.parser.ast.statements.loop.ForLoopStatement;
import com.kisslang.source.parser.ast.statements.loop.WhileLoopStatement;
import com.kisslang.source.parser.ast.statements.standart_lib.InputStatement;
import com.kisslang.source.parser.ast.statements.standart_lib.PrintLineStatement;
import com.kisslang.source.parser.ast.statements.standart_lib.PrintStatement;
import com.kisslang.source.parser.tokenization.Token;
import com.kisslang.source.parser.tokenization.TokenType;

import java.util.List;

/*
 * Copyright (C) 2019 The KISSlang Project by Vitalii Vorobii
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

public final class Parser {

    private static final Token EOF = new Token(TokenType.EOF, "");

    private final List<Token> tokens;
    private final int size;

    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
    }

    public Statement parse() {

        final BlockStatement result = new BlockStatement();

        while (!match(TokenType.EOF)) {
            result.addStatement(statement());
        }

        return result;
    }

    private BlockStatement block(){

        final BlockStatement blockOfStatements=new BlockStatement();

        consume(TokenType.LPAREN_FIGURE);

        while (!match(TokenType.RPAREN_FIGURE)){
            if(match(TokenType.EOF)){
                throw new RuntimeException("Expected } , but found end of file");
            }
            blockOfStatements.addStatement(statement());
        }

        return blockOfStatements;
    }

    private Statement blockOrSingle(){

        if(get(0).getType()==TokenType.LPAREN_FIGURE){
            return block();
        }
        return statement();
    }

    private Statement statement(){

        if (match(TokenType.PRINT)){
            return new PrintStatement(expression());
        }
        if (match(TokenType.PRINTLINE)){
            return new PrintLineStatement(expression());
        }
        if (match(TokenType.INPUT)){
            return Input();
        }
        if(match(TokenType.IF)){
            return IfElse();
        }
        if(match(TokenType.WHILE)){
            return While();
        }
        if(match(TokenType.FOR)){
            return For();
        }
        if(match(TokenType.BREAK)){
            return Break();
        }
        if(match(TokenType.CONTINUE)){
            return Continue();
        }

        return assignmentStatement();
    }

    private Statement Input(){
        next();
        if(get(0).getType()==TokenType.WORD){
            Token current=get(0);
            next();
            return new InputStatement(current.getText());
        }

        throw new RuntimeException("Wrong input statement usage");

    }

    private Statement Continue() {
        next();
        return new ContinueLoopStatement();
    }

    private Statement Break() {
        next();
        return new BreakLoopStatement();
    }


    private Statement assignmentStatement() {
        final Token current=get(0);
        if (current.getType()==TokenType.WORD && get(1).getType()==TokenType.ASSIGN){
            consume(TokenType.WORD);
            final String varName=current.getText();
            consume(TokenType.ASSIGN);
            return new AssignmentStatement(varName,expression());
        }
        throw new RuntimeException("Unknown operator!");
    }

    private Statement IfElse() {
        final Expression condition=expression();
        final Statement ifStatement=blockOrSingle();
        Statement elseStatement=null;
        if(match(TokenType.ELSE)) {
            elseStatement = blockOrSingle();
        }
        return new IfConditionalStatement(condition,ifStatement,elseStatement);
    }

    private Statement While() {
        final Expression condition=expression();
        final Statement statementIfTrue=blockOrSingle();
        return new WhileLoopStatement(condition,statementIfTrue);
    }

    private Statement For(){
        next();
        consume(TokenType.LPAREN,"No expected (");
        final Statement init=assignmentStatement();
        consume(TokenType.DELIMITER_FOR,"No expected delimiter after initializing");
        final Expression term=expression();
        consume(TokenType.DELIMITER_FOR,"No expected delimiter after expression");
        final Statement incr=assignmentStatement();
        consume(TokenType.RPAREN,"No expected )");
        final Statement statements=blockOrSingle();

        return new ForLoopStatement(init,term,incr,statements);

    }

    private Expression expression() {
        return additive();
    }

    private Expression additive() {
        Expression result = conditional();

        while (true) {
            if (match(TokenType.PLUS)) {
                result = new ArithmeticBinaryExpression('+', result, multiplicative());
                continue;
            }
            if (match(TokenType.MINUS)) {
                result = new ArithmeticBinaryExpression('-', result, multiplicative());
                continue;
            }
            break;
        }

        return result;
    }

    private Expression conditional(){
        Expression result = multiplicative();

        while (true) {
            if (match(TokenType.EQUAL)) {
                    result = new ConditionalExpression("==", result, multiplicative());
                    continue;
            }
            if (match(TokenType.LOWER_THAN)) {
                    result = new ConditionalExpression("<", result, multiplicative());
                    continue;
            }
            if (match(TokenType.GREATER_THAN)) {
                    result = new ConditionalExpression(">", result, multiplicative());
                    continue;
            }
            if (match(TokenType.GREATER_OR_EQUAL_THAN)) {
                result = new ConditionalExpression(">=", result, multiplicative());
                continue;
            }
            if (match(TokenType.LOWER_OR_EQUAL_THAN)) {
                result = new ConditionalExpression("<=", result, multiplicative());
                continue;
            }
            break;
        }

        return result;

    }

    private Expression multiplicative() {
        Expression result = logicalOr();

        while (true) {
            // 2 * 6 / 3
            if (match(TokenType.STAR)) {
                result = new ArithmeticBinaryExpression('*', result, logicalOr());
                continue;
            }
            if (match(TokenType.POW)) {
                result = new ArithmeticBinaryExpression('^', result, logicalOr());
                continue;
            }
            if (match(TokenType.SLASH)) {
                result = new ArithmeticBinaryExpression('/', result, logicalOr());
                continue;
            }
            break;
        }

        return result;
    }


    private Expression logicalOr(){

        Expression result = logicalOAnd();

        while (true) {

            if (match(TokenType.OR2)) {
                result = new LogicalBinaryExpression("||", result, logicalOAnd());
                continue;
            }
            if(match(TokenType.OR)){
                result=new LogicalBinaryExpression("|",result,logicalOAnd());
            }
            break;
        }

        return result;

    }

    private Expression logicalOAnd(){

        Expression result = unary();

        while (true) {

            if (match(TokenType.AND2)) {
                result = new LogicalBinaryExpression("&&", result, unary());
                continue;
            }
            if(match(TokenType.AND)){
                result=new LogicalBinaryExpression("&",result,logicalOAnd());
            }
            break;
        }

        return result;

    }

    private Expression unary() {

        if (match(TokenType.MINUS)) {
            return new UnaryExpression('-', primary());
        }
        if (match(TokenType.PLUS)) {
            return primary();
        }
        if (match(TokenType.NOT)) {
            return new LogicalUnaryExpression('!', primary());
        }

        return primary();
    }

    private Expression primary() {
        final Token current = get(0);
        if (match(TokenType.NUMBER)) {
            return new NumberExpression(Double.parseDouble(current.getText()));
        }
        if (match(TokenType.HEX_NUMBER)) {
            return new NumberExpression(Long.parseLong(current.getText(), 16));
        }
        if (match(TokenType.WORD)){
            return new ConstantExpression(current.getText());
        }
        if(match(TokenType.STRING_TEXT)){
            return new StringExpression(current.getText());
        }
        if (match(TokenType.LPAREN)) {
            Expression result = expression();
            match(TokenType.RPAREN);
            return result;
        }

        throw new RuntimeException("Unknown expression "+current+" !");
    }

    private Token consume(TokenType type){
        final Token current=get(0);
        if (type != current.getType()) throw new RuntimeException(current+" doesn't match "+type);
        pos++;
        return current;
    }

    private Token consume(TokenType type,String message){
        final Token current=get(0);
        if (type != current.getType()) throw new RuntimeException(message);
        pos++;
        return current;
    }

    private boolean match(TokenType type) {
        final Token current = get(0);
        if (type != current.getType()) return false;
        pos++;
        return true;
    }

    private void next(){
        pos++;
    }

    private Token get(int relativePosition) {
        final int position = pos + relativePosition;
        if (position >= size) return EOF;
        return tokens.get(position);
    }
}