package com.kisslang.source.parser;

import com.kisslang.source.library.Value;
import com.kisslang.source.library.keys.VariableKey;
import com.kisslang.source.parser.ast.expression.*;
import com.kisslang.source.parser.ast.expression.array.ArrayAccessGettingExpression;
import com.kisslang.source.parser.ast.expression.array.ArrayCreateExpression;
import com.kisslang.source.parser.ast.expression.binary.ArithmeticBinaryExpression;
import com.kisslang.source.parser.ast.expression.binary.ArrowBinaryExpression;
import com.kisslang.source.parser.ast.expression.binary.ConditionalExpression;
import com.kisslang.source.parser.ast.expression.binary.LogicalBinaryExpression;
import com.kisslang.source.parser.ast.expression.functional.ImmutableFunctionalCallExpression;
import com.kisslang.source.parser.ast.expression.functional.MutableFunctionalCallExpression;
import com.kisslang.source.parser.ast.expression.literal.BooleanExpression;
import com.kisslang.source.parser.ast.expression.literal.NumberExpression;
import com.kisslang.source.parser.ast.expression.literal.StringExpression;
import com.kisslang.source.parser.ast.expression.object.ImmutableObjectAccessGettingExpression;
import com.kisslang.source.parser.ast.expression.object.MutableObjectAccessGettingExpression;
import com.kisslang.source.parser.ast.expression.object.ObjectCreateExpression;
import com.kisslang.source.parser.ast.expression.unary.LogicalUnaryExpression;
import com.kisslang.source.parser.ast.expression.unary.UnaryExpression;
import com.kisslang.source.parser.ast.statements.*;
import com.kisslang.source.parser.ast.statements.assignement.*;
import com.kisslang.source.parser.ast.statements.condition.IfConditionalStatement;
import com.kisslang.source.parser.ast.statements.functional.*;
import com.kisslang.source.parser.ast.statements.loop.*;
import com.kisslang.source.parser.ast.statements.standart_lib.InputStatement;
import com.kisslang.source.parser.ast.statements.standart_lib.PrintLineStatement;
import com.kisslang.source.parser.ast.statements.standart_lib.PrintStatement;
import com.kisslang.source.parser.tokenization.Token;
import com.kisslang.source.parser.tokenization.TokenType;

import java.util.ArrayList;
import java.util.HashMap;
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

    private static final Token EOF = new Token ( TokenType.EOF , "" );

    private final List<Token> tokens;
    private final int size;

    private int pos;

    public Parser ( List<Token> tokens ) {
        this.tokens = tokens;
        size = tokens.size ( );
    }

    public Statement parse () {

        final BlockStatement result = new BlockStatement ( );

        while (!match ( TokenType.EOF )) {
            result.addStatement ( statement ( ) );
        }

        return result;
    }

    private BlockStatement block () {

        final BlockStatement blockOfStatements = new BlockStatement ( );

        consume ( TokenType.LPAREN_FIGURE );

        while (!match ( TokenType.RPAREN_FIGURE )) {
            if ( match ( TokenType.EOF ) ) {
                throw new RuntimeException ( "Expected } , but found end of file" );
            }
            blockOfStatements.addStatement ( statement ( ) );
        }

        return blockOfStatements;
    }

    private Statement blockOrSingle () {

        if ( get ( 0 ).getType ( ) == TokenType.LPAREN_FIGURE ) {
            return block ( );
        }
        return statement ( );
    }

    private Statement statement () {

        if ( match ( TokenType.PRINT ) ) {
            return new PrintStatement ( expression ( ) );
        }
        if ( match ( TokenType.PRINTLINE ) ) {
            return new PrintLineStatement ( expression ( ) );
        }
        if ( match ( TokenType.INPUT ) ) {
            return Input ( );
        }
        if ( match ( TokenType.IF ) ) {
            return IfElse ( );
        }
        if ( match ( TokenType.WHILE ) ) {
            return While ( );
        }
        if ( match ( TokenType.DO_LOOP ) ) {
            return DoWhile ( );
        }
        if ( match ( TokenType.RETURN_FROM_METHOD ) ) {
            return Return ( );
        }
        if ( match ( TokenType.FOR ) ) {
            return For ( );
        }
        if ( match ( TokenType.BREAK ) ) {
            return Break ( );
        }
        if ( match ( TokenType.CONTINUE ) ) {
            return Continue ( );
        }
        if ( match ( TokenType.FUNCTION_DECLARATION ) ) {
            return FunctionDeclaration ( );
        }
        if ( get ( 0 ).getType ( ) == TokenType.IMMUTABLE_NAME && get ( 1 ).getType ( ) == TokenType.LPAREN ) {
            return new FunctionStatement ( ImmutableFunctionCall ( ) );
        }
        if ( get ( 0 ).getType ( ) == TokenType.MUTTABLE_NAME && get ( 1 ).getType ( ) == TokenType.LPAREN ) {
            return new FunctionStatement ( MutableFunctionCall ( ) );
        }

        return AssignementStatement ( );
    }


    private Statement Input () {
        next ( );
        if ( get ( 0 ).getType ( ) == TokenType.MUTTABLE_NAME ) {
            Token current = get ( 0 );
            next ( );
            return new InputStatement ( current.getText ( ) );
        }

        throw new RuntimeException ( "Wrong input statement usage" );

    }

    private Statement FunctionDeclaration () {

        final String functionName = get ( 0 ).getText ( );

        final TokenType functionNameType = get ( 0 ).getType ( );

        if ( get ( 0 ).getType ( ) != TokenType.IMMUTABLE_NAME && get ( 0 ).getType ( ) != TokenType.MUTTABLE_NAME ) {
            throw new RuntimeException ( "Expected function name" );
        }
        next ( );

        System.out.println ( functionName + " with type of " + functionNameType );

        consume ( TokenType.LPAREN , "Expected ( !" );

        final List<Argument> argNames = new ArrayList<> ( );

        while (!match ( TokenType.RPAREN )) {
            Token current = get ( 0 );
            if ( get ( 0 ).getType ( ) != TokenType.IMMUTABLE_NAME && get ( 0 ).getType ( ) != TokenType.MUTTABLE_NAME ) {
                throw new RuntimeException ( "Expected argument" );
            }
            Argument arg = null;
            if ( get ( 0 ).getType ( ) == TokenType.IMMUTABLE_NAME ) {
                arg = new Argument ( current.getText ( ) , true );
            } else {
                arg = new Argument ( current.getText ( ) , false );
            }
            argNames.add ( arg );
            next ( );
            match ( TokenType.DELIMITER_ARGS );
        }

        final Statement functionBody = blockOrSingle ( );

        if ( functionNameType == TokenType.IMMUTABLE_NAME ) {

            return new ImmutableFunctionAssignStatement ( functionName , argNames , functionBody );
        }

        return new MutableFunctionAssignStatement ( functionName , argNames , functionBody );

    }


    private Expression ArrayIndexGetter () {
        Token current = get ( 0 );
        consume ( TokenType.IMMUTABLE_NAME );
        final String varName = current.getText ( );
        List<Expression> indexes = new ArrayList<> ( );

        while (get ( 0 ).getType ( ) == TokenType.LPAREN_SQUARE) {
            consume ( TokenType.LPAREN_SQUARE , "Square bracked expected during array index getting" );
            Expression index = expression ( );
            indexes.add ( index );
            consume ( TokenType.RPAREN_SQUARE , "Square bracked expected during array index getting" );
        }

        for (Expression e :
                indexes) {
            System.out.print ( e.eval ( ) + "  " );
        }

        Value[] indexValues = new Value[indexes.size ( )];

        for (int i = 0; i < indexValues.length; i++) {
            indexValues[i] = indexes.get ( i ).eval ( );
        }

        return new ArrayAccessGettingExpression ( varName , indexValues );

    }

    private Expression ImmutableFunctionCall () {

        final String functionName = consume ( TokenType.IMMUTABLE_NAME , "Expected function name" ).getText ( );

        consume ( TokenType.LPAREN , "Expected ( !" );

        final ImmutableFunctionalCallExpression function = new ImmutableFunctionalCallExpression ( functionName );

        while (!match ( TokenType.RPAREN )) {
            function.addArgument ( expression ( ) );
            match ( TokenType.DELIMITER_ARGS );
        }

        return function;
    }

    private Expression MutableFunctionCall () {

        final String functionName = consume ( TokenType.MUTTABLE_NAME , "Expected function name" ).getText ( );

        consume ( TokenType.LPAREN , "Expected ( !" );

        final MutableFunctionalCallExpression function = new MutableFunctionalCallExpression ( functionName );

        while (!match ( TokenType.RPAREN )) {
            function.addArgument ( expression ( ) );
            match ( TokenType.DELIMITER_ARGS );
        }

        return function;
    }


    private Statement Continue () {
        next ( );
        return new ContinueLoopStatement ( );
    }

    private Statement Break () {
        next ( );
        return new BreakLoopStatement ( );
    }

    private Statement Return () {
        next ( );
        return new ReturnStatement ( expression ( ) );
    }

    private Statement AssignVariableOrConstant () {

        final Token current = get ( 0 );

        if ( current.getType ( ) == TokenType.MUTTABLE_NAME && get ( 1 ).getType ( ) == TokenType.ASSIGN ) {
            consume ( TokenType.MUTTABLE_NAME );
            final String varName = current.getText ( );
            consume ( TokenType.ASSIGN );
            return new AssignmentVariableStatement ( varName , expression ( ) );
        } else if ( current.getType ( ) == TokenType.IMMUTABLE_NAME && get ( 1 ).getType ( ) == TokenType.ASSIGN ) {
            consume ( TokenType.IMMUTABLE_NAME );
            final String varName = current.getText ( );
            consume ( TokenType.ASSIGN );
            return new AssignementConstantStatement ( varName , expression ( ) );
        }

        throw new RuntimeException ( "Error during variable assignement...." );

    }

    private Statement AssignArray () {

        final Token current = get ( 0 );

        consume ( TokenType.IMMUTABLE_NAME );
        final String varName = current.getText ( );
        List<Expression> indexes = new ArrayList<> ( );

        while (get ( 0 ).getType ( ) == TokenType.LPAREN_SQUARE) {
            consume ( TokenType.LPAREN_SQUARE , "Square bracked expected during array index getting" );
            Expression index = expression ( );
            indexes.add ( index );
            consume ( TokenType.RPAREN_SQUARE , "Square bracked expected during array index getting" );
        }

        Value[] indexValues = new Value[indexes.size ( )];

        for (int i = 0; i < indexValues.length; i++) {
            indexValues[i] = indexes.get ( i ).eval ( );
        }

        consume ( TokenType.ASSIGN );

        return new ArrayAssignementStatement ( varName , indexValues , expression ( ) );

    }

    private Statement ConstObjectAssignement () {

        final Token current = get ( 0 );

        consume ( TokenType.IMMUTABLE_NAME );
        final String objectName = current.getText ( );
        consume ( TokenType.ARROW , "Expected arrow operator -> " );
        boolean immutable = false;
        if ( get ( 0 ).getType ( ) == TokenType.IMMUTABLE_NAME ) {
            immutable = true;
        } else if ( get ( 0 ).getType ( ) == TokenType.MUTTABLE_NAME ) {
        } else {
            throw new RuntimeException ( "Expected field name" );
        }

        String fieldName = get ( 0 ).getText ( );

        next ( );
        consume ( TokenType.ASSIGN );

        return new ImmutableObjectAssignmentStatement ( objectName , new VariableKey ( fieldName , immutable ) , expression ( ) );

    }

    private Statement VarObjectAssignement () {

        final Token current = get ( 0 );

        consume ( TokenType.MUTTABLE_NAME );
        final String objectName = current.getText ( );
        consume ( TokenType.ARROW , "Expected arrow operator -> " );
        boolean immutable = false;
        if ( get ( 0 ).getType ( ) == TokenType.IMMUTABLE_NAME ) {
            immutable = true;
        } else if ( get ( 0 ).getType ( ) == TokenType.MUTTABLE_NAME ) {
        } else {
            throw new RuntimeException ( "Expected field name" );
        }

        String fieldName = get ( 0 ).getText ( );

        next ( );
        consume ( TokenType.ASSIGN );

        return new MutableObjectAssignmentStatement ( objectName , new VariableKey ( fieldName , immutable ) , expression ( ) );

    }


    private Statement AssignementStatement () {

        final Token current = get ( 0 );

        if ( (current.getType ( ) == TokenType.MUTTABLE_NAME || current.getType ( ) == TokenType.IMMUTABLE_NAME) && get ( 1 ).getType ( ) == TokenType.ASSIGN )
            return AssignVariableOrConstant ( );

        if ( current.getType ( ) == TokenType.IMMUTABLE_NAME && get ( 1 ).getType ( ) == TokenType.LPAREN_SQUARE )
            return AssignArray ( );

        if ( current.getType ( ) == TokenType.IMMUTABLE_NAME && get ( 1 ).getType ( ) == TokenType.ARROW )
            return ConstObjectAssignement ( );

        if ( current.getType ( ) == TokenType.MUTTABLE_NAME && get ( 1 ).getType ( ) == TokenType.ARROW )
            return VarObjectAssignement ( );

        throw new RuntimeException ( "Unknown operator!" + current + " Next " + get ( 1 ) + " -> " + get ( 2 ) );
    }

    private Statement IfElse () {
        final Expression condition = expression ( );
        final Statement ifStatement = blockOrSingle ( );
        Statement elseStatement = null;
        if ( match ( TokenType.ELSE ) ) {
            elseStatement = blockOrSingle ( );
        }
        return new IfConditionalStatement ( condition , ifStatement , elseStatement );
    }

    private Statement While () {
        final Expression condition = expression ( );
        final Statement statementIfTrue = blockOrSingle ( );
        return new WhileLoopStatement ( condition , statementIfTrue );
    }

    private Statement DoWhile () {
        final Statement statementOrBlock = blockOrSingle ( );
        consume ( TokenType.WHILE , "Expected condition during do-while loop declaration ..." );
        final Expression condition = expression ( );
        return new DoWhileLoopStatement ( statementOrBlock , condition );
    }

    private Statement For () {
//        next ( );
        consume ( TokenType.LPAREN , "No expected (" );
        final Statement init = AssignementStatement ( );
        consume ( TokenType.DELIMITER_FOR , "No expected delimiter after initializing" );
        final Expression term = expression ( );
        consume ( TokenType.DELIMITER_FOR , "No expected delimiter after expression" );
        final Statement incr = AssignementStatement ( );
        consume ( TokenType.RPAREN , "No expected )" );
        final Statement statements = blockOrSingle ( );

        return new ForLoopStatement ( init , term , incr , statements );

    }


    private Expression expression () {
        return arrow ( );
    }

    private Expression arrow () {

        Expression result = additive ( );

        while (true) {
            if ( match ( TokenType.ARROW ) ) {
                Token current = get ( 0 );
                VariableKey key;
                if ( current.getType ( ) == TokenType.MUTTABLE_NAME ) {
                    key = new VariableKey ( current.getText ( ) , false );
                } else if ( current.getType ( ) == TokenType.IMMUTABLE_NAME ) {
                    key = new VariableKey ( current.getText ( ) , true );
                } else {
                    throw new RuntimeException ( "Expected field" );
                }
                System.out.println ( "TEXT : " + current.getText ( ) + key.isImmutable ( ) + " " );
                next ( );
                result = new ArrowBinaryExpression ( result , key );
                continue;
            }
            break;
        }

        return result;
    }

    private Expression additive () {
        Expression result = conditional ( );

        while (true) {
            if ( match ( TokenType.PLUS ) ) {
                result = new ArithmeticBinaryExpression ( '+' , result , multiplicative ( ) );
                continue;
            }
            if ( match ( TokenType.MINUS ) ) {
                result = new ArithmeticBinaryExpression ( '-' , result , multiplicative ( ) );
                continue;
            }
            break;
        }

        return result;
    }

    private Expression conditional () {
        Expression result = multiplicative ( );

        while (true) {
            if ( match ( TokenType.EQUAL ) ) {
                result = new ConditionalExpression ( "==" , result , multiplicative ( ) );
                continue;
            }
            if ( match ( TokenType.LOWER_THAN ) ) {
                result = new ConditionalExpression ( "<" , result , multiplicative ( ) );
                continue;
            }
            if ( match ( TokenType.GREATER_THAN ) ) {
                result = new ConditionalExpression ( ">" , result , multiplicative ( ) );
                continue;
            }
            if ( match ( TokenType.GREATER_OR_EQUAL_THAN ) ) {
                result = new ConditionalExpression ( ">=" , result , multiplicative ( ) );
                continue;
            }
            if ( match ( TokenType.LOWER_OR_EQUAL_THAN ) ) {
                result = new ConditionalExpression ( "<=" , result , multiplicative ( ) );
                continue;
            }
            break;
        }

        return result;

    }

    private Expression multiplicative () {
        Expression result = logicalOr ( );

        while (true) {
            // 2 * 6 / 3
            if ( match ( TokenType.STAR ) ) {
                result = new ArithmeticBinaryExpression ( '*' , result , logicalOr ( ) );
                continue;
            }
            if ( match ( TokenType.POW ) ) {
                result = new ArithmeticBinaryExpression ( '^' , result , logicalOr ( ) );
                continue;
            }
            if ( match ( TokenType.SLASH ) ) {
                result = new ArithmeticBinaryExpression ( '/' , result , logicalOr ( ) );
                continue;
            }
            break;
        }

        return result;
    }


    private Expression logicalOr () {

        Expression result = logicalOAnd ( );

        while (true) {

            if ( match ( TokenType.OR2 ) ) {
                result = new LogicalBinaryExpression ( "||" , result , logicalOAnd ( ) );
                continue;
            }

            if ( match ( TokenType.OR ) ) {
                result = new LogicalBinaryExpression ( "|" , result , logicalOAnd ( ) );
                continue;
            }

            break;
        }

        return result;

    }

    private Expression logicalOAnd () {

        Expression result = unary ( );

        while (true) {

            if ( match ( TokenType.AND2 ) ) {
                result = new LogicalBinaryExpression ( "&&" , result , unary ( ) );
                continue;
            }
            if ( match ( TokenType.AND ) ) {
                result = new LogicalBinaryExpression ( "&" , result , unary ( ) );
                continue;
            }
            break;
        }

        return result;

    }

    private Expression unary () {

        if ( match ( TokenType.MINUS ) ) {
            return new UnaryExpression ( '-' , primary ( ) );
        }
        if ( match ( TokenType.PLUS ) ) {
            return primary ( );
        }
        if ( match ( TokenType.NOT ) ) {
            return new LogicalUnaryExpression ( '!' , primary ( ) );
        }

        return primary ( );
    }

    private Expression ArrayDeclaration () {

        List<Expression> listOfExpressions = new ArrayList<> ( );

        while (!match ( TokenType.RPAREN_SQUARE )) {
            listOfExpressions.add ( expression ( ) );
            match ( TokenType.DELIMITER_ARGS );
        }

        return new ArrayCreateExpression ( listOfExpressions );
    }

    private Expression ImmutableObjectGetter () {

        final String objectName = get ( 0 ).getText ( );

        next ( );

        consume ( TokenType.ARROW , "Expected -> operator ..." );

        boolean immutable = false;

        if ( get ( 0 ).getType ( ) == TokenType.IMMUTABLE_NAME ) {
            immutable = true;
        } else if ( get ( 0 ).getType ( ) == TokenType.MUTTABLE_NAME ) {
        } else {
            throw new RuntimeException ( "Expected field ..." );
        }

        final String fieldName = get ( 0 ).getText ( );

        next ( );

        return new ImmutableObjectAccessGettingExpression ( objectName , new VariableKey ( fieldName , immutable ) );

    }

    private Expression MutableObjectGetter () {

        final String objectName = get ( 0 ).getText ( );

        next ( );

        consume ( TokenType.ARROW , "Expected -> operator ..." );

        boolean immutable = false;

        if ( get ( 0 ).getType ( ) == TokenType.IMMUTABLE_NAME ) {
            immutable = true;
        } else if ( get ( 0 ).getType ( ) == TokenType.MUTTABLE_NAME ) {
        } else {
            throw new RuntimeException ( "Expected field ..." );
        }

        final String fieldName = get ( 0 ).getText ( );

        next ( );

        return new MutableObjectAccessGettingExpression ( objectName , new VariableKey ( fieldName , immutable ) );

    }

    private Expression ObjectDeclaration () {

        HashMap<VariableKey, Value> table = new HashMap<> ( );

        while (!match ( TokenType.RPAREN_FIGURE )) {

            VariableKey key;

            if ( get ( 0 ).getType ( ) == TokenType.IMMUTABLE_NAME ) {
                key = new VariableKey ( get ( 0 ).getText ( ) , true );
            } else if ( get ( 0 ).getType ( ) == TokenType.MUTTABLE_NAME ) {
                key = new VariableKey ( get ( 0 ).getText ( ) , false );
            } else {
                throw new RuntimeException ( "Expected key..." );
            }

            next ( );

            consume ( TokenType.ARROW , "Got key , expected value!" );

            table.put ( key , expression ( ).eval ( ) );

            match ( TokenType.DELIMITER_ARGS );

        }

        System.out.println ( get ( 0 ) );

        return new ObjectCreateExpression ( table );

    }

    private Expression primary () {
        final Token current = get ( 0 );
        if ( match ( TokenType.NUMBER ) ) {
            return new NumberExpression ( Double.parseDouble ( current.getText ( ) ) );
        }
        if ( match ( TokenType.TRUE_LITERAL ) ) {
            return new BooleanExpression ( true );
        }
        if ( match ( TokenType.FALSE_LITERAL ) ) {
            return new BooleanExpression ( false );
        }
        if ( match ( TokenType.HEX_NUMBER ) ) {
            return new NumberExpression ( Long.parseLong ( current.getText ( ) , 16 ) );
        }
        if ( get ( 0 ).getType ( ) == TokenType.IMMUTABLE_NAME && get ( 1 ).getType ( ) == TokenType.LPAREN ) {
            return ImmutableFunctionCall ( );
        }
        if ( get ( 0 ).getType ( ) == TokenType.IMMUTABLE_NAME && get ( 1 ).getType ( ) == TokenType.LPAREN_SQUARE ) {
            return ArrayIndexGetter ( );
        }
        if ( get ( 0 ).getType ( ) == TokenType.MUTTABLE_NAME && get ( 1 ).getType ( ) == TokenType.LPAREN ) {
            return MutableFunctionCall ( );
        }
        if ( match ( TokenType.LPAREN_FIGURE ) ) {
            return ObjectDeclaration ( );
        }
        if ( match ( TokenType.LPAREN_SQUARE ) ) {
            return ArrayDeclaration ( );
        }
        if ( match ( TokenType.MUTTABLE_NAME ) ) {
            return new VariableExpression ( current.getText ( ) );
        }
        if ( match ( TokenType.IMMUTABLE_NAME ) ) {
            return new ConstantExpression ( current.getText ( ) );
        }
        if ( match ( TokenType.STRING_TEXT ) ) {
            return new StringExpression ( current.getText ( ) );
        }
        if ( match ( TokenType.LPAREN ) ) {
            Expression result = expression ( );
            match ( TokenType.RPAREN );
            return result;
        }

        throw new RuntimeException ( "Unknown expression " + current + " !" );
    }


    private Token consume ( TokenType type ) {
        final Token current = get ( 0 );
        if ( type != current.getType ( ) ) throw new RuntimeException ( current + " doesn't match " + type );
        pos++;
        return current;
    }

    private Token consume ( TokenType type , String message ) {
        final Token current = get ( 0 );
        if ( type != current.getType ( ) ) throw new RuntimeException ( message + "\n" + get ( 0 ) + " " + get ( 1 ) );
        pos++;
        return current;
    }

    private boolean match ( TokenType type ) {
        final Token current = get ( 0 );
        if ( type != current.getType ( ) ) return false;
        pos++;
        return true;
    }

    private void next () {
        pos++;
    }

    private Token get ( int relativePosition ) {
        final int position = pos + relativePosition;
        if ( position >= size ) return EOF;
        return tokens.get ( position );
    }

}