package com.kisslang.source.library;

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

import java.util.Arrays;

public class ArrayValue implements Value {

    private Value[] array;
    private int size;


    public ArrayValue(int size){
        this.array=new Value[size];
        this.size=size;
    }

    private void confirmBounds(int index){
        if(index>size){
            throw new RuntimeException("Index out of Array bounds !");
        }
    }

    public ArrayValue(Value [] array){
        this.size=array.length;
        this.array=new Value[size];
        System.arraycopy(array,0,this.array,0,size);
    }

    public ArrayValue(ArrayValue o){
        this(o.array);
    }

    public Value getElement(int index){
        confirmBounds(index);
        return array[index];
    }

    public void setElement(int index,Value value){
        confirmBounds(index);
        array[index]=value;
    }

    @Override
    public boolean asBoolean() {
        return false;
    }

    @Override
    public String asString() {
        return Arrays.toString(array);
    }

    @Override
    public String toString() {
        return asString();
    }

    @Override
    public double asNumber() {
        throw new RuntimeException("Cannot cast array to number");
    }

    @Override
    public boolean canBeRepresentedAsNumber() {
        return false;
    }

    @Override
    public boolean isString() {
        return false;
    }

}
