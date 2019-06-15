package com.kisslang.source.parser.ast.statements;

import com.kisslang.source.library.Variables;
import com.kisslang.source.library.value.NumberValue;
import com.kisslang.source.library.value.Value;
import com.kisslang.source.parser.ast.helper.InputRecognizer;


import java.util.Scanner;

public class InputStatement implements Statement {

    private final String variableName;

    public InputStatement(String variableName){
        this.variableName=variableName;
    }

    @Override
    public void execute()  {


        synchronized (Variables.class){
            Scanner scanner = new Scanner(System.in);
            String line="";
            while (true) {
                line = scanner.nextLine();
                if (!line.isEmpty()){
                    Variables.add(variableName,new InputRecognizer(line).recognize());
                    break;
                }
            }

        }


    }

}
