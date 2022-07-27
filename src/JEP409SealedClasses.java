import java.util.Arrays;

interface Celestial {
}

/**
 * 使用密封的类和接口增强 Java 编程语言 。密封的类和接口限制了哪些其他类或接口可以扩展或实现它们。
 *
 * <h1>允许的子类的约束</h1>
 * <li>它们必须在编译时可由密封类访问</li>
 * <li>他们必须直接扩展密封类</li>
 * <li>它们必须恰好具有以下修饰符之一来描述它如何继续由其超类发起的密封：
 *
 * final: 无法进一步扩展
 *
 * sealed: 只能由其允许的子类扩展
 *
 * non-sealed：可以被未知的子类扩展；密封类不能阻止其允许的子类这样做</li>
 * <a href="http://openjdk.org/jeps/409">SealedClasses</a>
 * <p>
 * <h2>Two method about sealed class </h2>
 * {@link Class#isSealed()} <br/>
 * {@link Class#getPermittedSubclasses()}
 * </p>
 */
class JEP409SealedClasses {
    public static void main(String[] args) {
        Rectangle shape = new Rectangle();
        Class<? extends Rectangle> aClass = shape.getClass();
        System.out.println(aClass.isSealed());
        Arrays.stream(aClass.getPermittedSubclasses()).forEach(System.out::println);
    }
}

final class Planet implements Celestial {
}

final class Star implements Celestial {
}

final class Comet implements Celestial {
}

/**
 * {@link Square}
 */
abstract sealed class Shape implements Celestial
    permits Circle, Rectangle, Square, WeirdShape {
}

final class Circle extends Shape {
}

sealed class Rectangle extends Shape
    permits TransparentRectangle, FilledRectangle {
    public double length, width;
}

final class TransparentRectangle extends Rectangle {
}

final class FilledRectangle extends Rectangle {
}

non-sealed class WeirdShape extends Shape {
}

abstract sealed class Root {
    final class A extends Root {
    }

    final class B extends Root {
    }

    final class C extends Root {
    }
}


/*
sealed interface Expr
    permits ConstantExpr, PlusExpr, TimesExpr, NegExpr {
    int eval();
}

final class ConstantExpr implements Expr {
    int i;
    ConstantExpr(int i) { this.i = i; }
    public int eval() { return i; }
}

final class PlusExpr implements Expr {
    Expr a, b;
    PlusExpr(Expr a, Expr b) { this.a = a; this.b = b; }
    public int eval() { return a.eval() + b.eval(); }
}

final class TimesExpr implements Expr {
    Expr a, b;
    TimesExpr(Expr a, Expr b) { this.a = a; this.b = b; }
    public int eval() { return a.eval() * b.eval(); }
}

final class NegExpr implements Expr {
    Expr e;
    NegExpr(Expr e) { this.e = e; }
    public int eval() { return -e.eval(); }
}
*/

sealed interface Expr
    permits ConstantExpr, PlusExpr, TimesExpr, NegExpr {
    int eval();
}

record ConstantExpr(int i) implements Expr {
    public int eval() { return i(); }
}

record PlusExpr(Expr a, Expr b) implements Expr {
    public int eval() { return a.eval() + b.eval(); }
}

record TimesExpr(Expr a, Expr b) implements Expr {
    public int eval() { return a.eval() * b.eval(); }
}

record NegExpr(Expr e) implements Expr {
    public int eval() { return -e.eval(); }
}