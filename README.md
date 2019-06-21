## KISSlang

###### Sample JVM JIT compiler for a sample language created for educational purposes.

## A few examples:

##### Sample "Hello, world!" program:
```
PrintLine 'Hello, world!'
```
##### Some math & variables:
```
$x = 10
$y = 3
PrintLine ($x+$y)*2
```
##### Some user input:
```
Input line
PrintLine "User`s input: "+line
```
##### Some if/else statements
```
$i=0
If ($i==0)
    Print "i is equal to zero"
Else
    Print "i is not equal to zero"
```
##### Some while loops
```
$i=10
While ($i>0){
    PrintLine $i
    $i=$i-1
}
PrintLine $i
```
##### Some for loops
```
For($i=0;$i<10;$i++){
    Print $i
}
```

##### Square of circle recognizing
```
Input $r
PrintLine PI*($r^2)
```

##### Some functions 
```
Function $func1($name1){
    PrintLine $name1
}

$func1("KISSLANG")

Function $func1($name1){
    PrintLine $name1+" has changed! "
}

$func1("KISSLANG")
```
##### Functions that assigned with $ are muttable, while those who assigned without $ are immutable
```
Function func2($name3){
    PrintLine $name3
}

Function func2($name3){ //Error
    PrintLine $name3+" changed"
}
```
##### Auto function recognizing
```
Function sum(a,b){
    PrintLine "Calling sum with 2 arguments"
    Return a+b
}

PrintLine sum(2,4)

Function sum(a,b,c){
    PrintLine "Calling sum with 3 arguments"
    Return a+b+c
}

PrintLine sum(2,1,3)
```

#####  Some arrays
````

arr1=Array(sum(2,4),1,3,5,6)

PrintLine arr1

arr2=[2,1,3,49,"Str",5]

PrintLine arr2

````

##### Multidemensional arrays

````
arr=[2,1,3,[0,49,5]]
PrintLine arr[3][2]
````
##### Key->Value pairs (Objects)
````
$a={$key->"value",another_key->"another key"}

PrintLine $a
````

