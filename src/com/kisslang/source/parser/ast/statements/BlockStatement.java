package com.kisslang.source.parser.ast.statements;

import java.util.LinkedList;
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

public class BlockStatement implements Statement {

    private List<Statement> statements;

    public BlockStatement(List<Statement> statements){
        this.statements=statements;
    }

    public BlockStatement(){
        this.statements=new LinkedList<>();
    }

    public void addStatement(Statement statement){
        statements.add(statement);
    }

    @Override
    public void execute() {

        for (Statement s: statements) {

            s.execute();

        }

    }

    @Override
    public String toString() {
        StringBuilder strBuild=new StringBuilder();
        for (Statement s: statements){
            strBuild.append(s);
        }
        return strBuild.toString();
    }
}
