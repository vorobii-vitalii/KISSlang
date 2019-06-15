package com.kisslang.source.parser.ast.statements.standart_lib;

import com.kisslang.source.library.VariableKey;
import com.kisslang.source.library.Variables;
import com.kisslang.source.parser.ast.helper.InputRecognizer;
import com.kisslang.source.parser.ast.statements.Statement;


import java.util.Scanner;

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
                    Variables.add(new VariableKey(variableName,false),new InputRecognizer(line).recognize());
                    break;
                }
            }

        }


    }

}
