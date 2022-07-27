import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

enum Argument {
    ARRAY(List.class),
    MAP(Map.class),
    OBJECT(Object.class);

    private final Class<?> type;

    Argument(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }
}

/**
 * <a href="https://openjdk.org/projects/amber/guides/lvti-faq">QA</a> <br/>
 * <a href="https://openjdk.org/jeps/193">Local-Variable Type Inference</a> <br/>
 * <a href="https://openjdk.org/projects/amber/guides/lvti-style-guide">how to use var</a> <br/>
 *
 * syntactic sugar<br/>
 * <img src="../msic/desugar.png" alt="" width="1798" height="787" />
 */
public class JEP286LocalVariableTypeInference {
    // error
    // var s = "a";
    public static void main(String[] args) {
        // int
        var i = 3;
        // String
        var s = "String";
        var l = List.of(1, 2, 3, 4, 5);
        var stream = l.stream();
        var m = Map.of(1, "a", 2, "b", 3, "c");
        var e = m.entrySet();

        final var builder = new StringBuilder();
        var t = Argument.OBJECT;

        for (var x : l) {
            // do something
            System.out.println(x);
        }
        for (var c = 0; c < 10; c++) {
            // do something
        }

        try (var fis = new FileInputStream("/")) {

        } catch (Exception ignore) {

        }

        BiFunction<Integer,Integer,Integer> fun = (var a, var b) -> a + b;
        // same as:
        // BiFunction<Integer,Integer,Integer> fun = (a, b) -> a + b;
        // same as:
        // BiFunction<Integer,Integer,Integer> fun = Integer::sum;

        // error
        // ====================================
        // not allowed null
        // var n = null;
        //  initializer required on the right-hand side of var
        // var o;
        // o = "first";
        // var x = System.out::println;

        // (var x, y) -> x.process(y)      // Cannot mix var and inferred formal parameters
        // in implicitly typed lambda expressions
        // (var x, int y) -> x.process(y)  // Cannot mix var and manifest types
        // in explicitly typed lambda expressions
        // ====================================
    }
}