package com.kisslang.source.compilation;

import com.kisslang.source.parser.lex_analysis.Lexer;
import com.kisslang.source.parser.Parser;
import com.kisslang.source.parser.tokenization.Token;
import com.kisslang.source.parser.ast.statements.Statement;

import java.util.List;

public class KISSCompiler {

    private String codeToBeCompiled;

    private List<Token> lexerTokens;

    private boolean expressionsProvided;

    private Statement expressionsKISS;

    private volatile static KISSCompiler compilerSingleton;

    private KISSCompiler(String codeToBeCompiled) {
        this.codeToBeCompiled=codeToBeCompiled;
        expressionsProvided=false;
    }

    private KISSCompiler(){
        this.codeToBeCompiled="";
        expressionsProvided=false;
    }

    boolean isReady() {
        return expressionsProvided;
    }

    public String getSourceCode(){
        return codeToBeCompiled;
    }

    public static KISSCompiler getInstance(String code) {

        if (compilerSingleton==null){
            compilerSingleton=new KISSCompiler(code);
        }
        return compilerSingleton;
    }

    public static KISSCompiler getInstance() {

        if (compilerSingleton==null){
            compilerSingleton=new KISSCompiler();
        }
        return compilerSingleton;
    }

    public void execute() throws java.io.IOException {

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
