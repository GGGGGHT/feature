[toc]

# JDK8之后的Java新特性



## jdk9

- 模块化[^200] 将JDK拆分为一级模块,这些模块可以在编译,构建和运行时组合成各种配置
- jshell[^222]    Read-Eval-Print Loop
- Compact Strings[^254]  `String` ,`AbstractStringBuilder `等底层数据结构由`char[]`转为`byte[]` 用以节省内存空间
- Spin-Wait Hints[^285 ] 只是一个提示操作 不携带任何行为要求。 允许JVM在某些硬件平台上可能获得收益 `Thread.onSpinWait()`
- Stack-Walking[^6] 流式的获取当前线程栈 [SpringBoot PR](https://github.com/spring-projects/spring-boot/pull/31701)
- Enhanced Method Handles[^274] 适用于反射 
- Variable Handles[^193] 同上

## jdk10

*  Local-Variable Type Inference[^286]  局部类型自动推断 `var`关键字



## jdk11

- Local-Variable Syntax for Lambda Parameters `var`关键字适配lambda表达式

* Flight Recorder[^328] 
* ZGC: A Scalable Low-Latency Garbage Collector[^333]

## jdk12

* Switch Expressions (Preview)[^325] 
* Switch Expressions (Second Preview)][^354]
* Switch Expressionos[^361]



## jdk13

* Text Block[^368] 文本块

## jdk14

* Pattern Matching for instanceof[^305]  模式匹配 不用手动强转

## jdk15



## jdk 17

* Records[^395] 充当不可变数据的透明载体的类

## jdk 19

* Virtual Threads (Preview)[^425] Java的协程





# see also

[oracle](https://docs.oracle.com/en/java/javase/18/language/preface.html#GUID-840E8268-A821-4BCE-83FE-A4ACAAED68DA)



---

[^200]: https://openjdk.org/jeps/200
[^222]: https://openjdk.org/jeps/222
[^254]:https://openjdk.org/jeps/254
[^285]: https://openjdk.org/jeps/285
[^286]: https://openjdk.org/jeps/286 
[^305]: https://openjdk.org/jeps/305
[^193]: https://openjdk.org/jeps/193
[^274]: https://openjdk.org/jeps/274
[^325]: https://openjdk.org/jeps/325
[^354]: https://openjdk.org/jeps/354 
[^361]: https://openjdk.org/jeps/361
[^328]: https://openjdk.org/jeps/328
[^333]: https://openjdk.org/jeps/333
[^368]: https://openjdk.org/jeps/368

