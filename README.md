# JDK8之后的Java新特性

<b>Java Language Specification, JLS </b>

> This is a specification for the Java Language. The JLS specifies the syntax for the Java
> programming language and other rules that say what is or is not a valid Java program. It also
> specifies what a program means; i.e. what happens when you run a (valid) program.

<b>Java Specification Request, JSR</b>
> A JSR is a document created as part of the Java Community Process (JCP) that is setting the scope
> for a team of people to develop a new specification. These specifications are (AFAIK) always Java
> related, but they frequently address things that are not going to be core Java SE or Java EE
> technology. A typical JSR's subject material is a relatively mature technology; i.e. on that is in a
> state that can be specified. (If you try produce a specification too early, then you typically end
> up with a bad spec. Other things can lead to that too.)

<b>Java Enhancement Proposal, JEP</b>

> A JEP is a document that is proposing an enhancement to Java core technology. These proposals are
> typically for enhancements that are not ready to be specified yet. As the JEP-0 document states,
> JEPs may call for exploration of novel (even "whacky") ideas. Generally speaking, prototyping will
> be required to separate the viable and non-viable ideas and clarify them to the point where a
> specification can be produced.

## jdk9

- 模块化[^200] 将JDK拆分为一级模块,这些模块可以在编译,构建和运行时组合成各种配置
- jshell[^222]    Read-Eval-Print Loop
- Compact Strings[^254]  `String` ,`AbstractStringBuilder `等底层数据结构由`char[]`转为`byte[]`
  用以节省内存空间
- Stack-Walking[^259] 流式的获取当前线程栈 [SpringBoot PR](https://github.com/spring-projects/spring-boot/pull/31701)
- Enhanced Method Handles[^274] 适用于反射
- Variable Handles[^193] 同上

## jdk10

* Local-Variable Type Inference[^286]  局部类型自动推断 `var`关键字
* G1 并行Full GC[^307 ] [More Detail]( https://www.oracle.com/technical-resources/articles/java/g1gc.html#:~:text=Phases%20of%20the%20Marking%20Cycle)

## jdk11

- Local-Variable Syntax for Lambda Parameters `var`关键字适配lambda表达式

* Flight Recorder[^328] 
* ZGC: A Scalable Low-Latency Garbage Collector[^333]

## jdk12

* Switch Expressions (Preview)[^325]

## jdk13

* Text Block[^368] 文本块

## jdk14

* Pattern Matching for instanceof[^305]  模式匹配 不用手动强转
* Helpful NullPointerExceptions[^358] 增强NPE

## jdk 17

* Records[^395] 充当不可变数据的透明载体的类

## jdk 19

* Virtual Threads (Preview)[^425]
  Java的协程 [VirtualThread](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/lang/Thread.java#L831)

## cmd

[jcmd](https://docs.oracle.com/en/java/javase/17/docs/specs/man/jcmd.html)

[jlink](https://docs.oracle.com/en/java/javase/17/docs/specs/man/jlink.html)

[jhsdb](https://docs.oracle.com/en/java/javase/17/docs/specs/man/jhsdb.html)

```sh
java --list-modules 
java --describe-module ${module-name} 

```

# see also

[oracle](https://docs.oracle.com/en/java/javase/18/language/preface.html#GUID-840E8268-A821-4BCE-83FE-A4ACAAED68DA)

| Release         | GA Date        | Premier Support Until | Extended Support Until | Sustaining Support |
| :-------------- | :------------- | :-------------------- | :--------------------- | :----------------- |
| 7 (LTS)         | July 2011      | July 2019             | July 2022*****         | Indefinite         |
| 8﻿ (LTS)**       | March 2014     | March 2022            | December 2030*****     | Indefinite         |
| 9 (non‑LTS)     | September 2017 | March 2018            | Not Available          | Indefinite         |
| 10 (non‑LTS)    | March 2018     | September 2018        | Not Available          | Indefinite         |
| 11 (LTS)        | September 2018 | September 2023        | September 2026         | Indefinite         |
| 12 (non‑LTS)    | March 2019     | September 2019        | Not Available          | Indefinite         |
| 13 (non‑LTS)    | September 2019 | March 2020            | Not Available          | Indefinite         |
| 14 (non‑LTS)    | March 2020     | September 2020        | Not Available          | Indefinite         |
| 15 (non‑LTS)    | September 2020 | March 2021            | Not Available          | Indefinite         |
| 16 (non-LTS)    | March 2021     | September 2021        | Not Available          | Indefinite         |
| 17 (LTS)        | September 2021 | September 2026****    | September 2029****     | Indefinite         |
| 18 (non-LTS)    | March 2022     | September 2022        | Not Available          | Indefinite         |
| 19 (non-LTS)*** | September 2022 | March 2023            | Not Available          | Indefinite         |
| 20 (non-LTS)*** | March 2023     | September 2023        | Not Available          | Indefinite         |
| 21 (LTS)***     | September 2023 | September 2028        | September 2031         | Indefinite         |



访问权限修饰符

![img](https://media.geeksforgeeks.org/wp-content/cdn-uploads/Access-Modifiers-in-Java.png)


---

[^200]: https://openjdk.org/jeps/200

[^222]: https://openjdk.org/jeps/222

[^254]: https://openjdk.org/jeps/254

[^259]: https://openjdk.org/jeps/259

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

[^358]: https://openjdk.org/jeps/358
[^307]: https://openjdk.org/jeps/307
[^425]: https://openjdk.org/jeps/425

