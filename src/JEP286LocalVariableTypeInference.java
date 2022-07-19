import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

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
 * <a href="https://openjdk.org/jeps/193">Local-Variable Type Inference</a>
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

        try (var fis = new FileInputStream("/")) {

        } catch (Exception ignore) {

        }

        // error
        // ====================================
        // not allowed null
        // var n = null;
        //  initializer required on the right-hand side of var
        // var o;
        // o = "first";
        // var x = System.out::println;
        // ====================================
    }
}