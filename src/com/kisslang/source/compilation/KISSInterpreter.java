package com.kisslang.source.compilation;

import com.kisslang.source.parser.lex_analysis.Lexer;
import com.kisslang.source.parser.Parser;
import com.kisslang.source.parser.tokenization.Token;
import com.kisslang.source.parser.ast.statements.Statement;

import java.util.List;

public class KISSInterpreter {

    private String codeToBeCompiled;

    private List<Token> lexerTokens;

    private boolean expressionsProvided;

    private Statement expressionsKISS;

    private volatile static KISSInterpreter compilerSingleton;

    private KISSInterpreter(String codeToBeCompiled) {
        this.codeToBeCompiled=codeToBeCompiled;
        expressionsProvided=false;
    }

    private KISSInterpreter(){
        this.codeToBeCompiled="";
        expressionsProvided=false;
    }

    boolean isReady() {
        return expressionsProvided;
    }

    public String getSourceCode(){
        return codeToBeCompiled;
    }

    public static KISSInterpreter getInstance(String code) {

        if (compilerSingleton==null){
            compilerSingleton=new KISSInterpreter(code);
        }

        return compilerSingleton;
    }

    public static KISSInterpreter getInstance() {

        if (compilerSingleton==null){
            compilerSingleton=new KISSInterpreter();
        }
        return compilerSingleton;
    }

    public void execute() {

        provideInit();

        expressionsKISS.execute();


    }

    public void provideInit() {

        if(!isReady()) {
            init();
        }

    }

    public void printStatements() {

        provideInit();

        System.out.println(expressionsKISS);

    }

    public void printTokens() {

        provideInit();

        for (Token e: lexerTokens) {
            System.out.println(e);
        }

    }

    public void setSourceCode(String codeToBeCompiled) {

        this.codeToBeCompiled = codeToBeCompiled;

        expressionsProvided=false;

    }

    public void init() {

        lexerTokens=new Lexer(codeToBeCompiled).tokenize();

        expressionsKISS=new Parser(lexerTokens).parse();

        expressionsProvided=true;
    }


}
