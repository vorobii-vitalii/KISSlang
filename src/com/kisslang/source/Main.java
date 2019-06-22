package com.kisslang.source;

import com.kisslang.source.compilation.KISSInterpreter;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main ( String[] args ) throws java.io.IOException {

        String content = new String ( Files.readAllBytes ( Paths.get ( "/home/vitalii/eclipse-workspace/KISSlang/src/com/kisslang/source/program.kiss" ) ) );

        KISSInterpreter compiler = KISSInterpreter.getInstance ( content );

        System.out.println ( compiler.getSourceCode ( ) );

        System.out.println ( );
        System.out.println ( );

        compiler.execute ( );

        System.out.println ( );

        //compiler.printTokens();

    }
}
