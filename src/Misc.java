
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.Float.NaN;

public class Misc {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/hsperfdata_ubuntu/2203");
        // byte[] bytes = Files.readAllBytes(path);
        // File file = new File(path);
        InputStream is = Files.newInputStream(path);
        ByteBuffer buffer = ByteBuffer.allocate(is.available());
        is.read(buffer.array());
        System.out.println(buffer);
        System.out.println(Arrays.toString(buffer.array()));


        List<Integer> integers = List.of(1, 2, 3);
        // uoe
        // integers.add(1);
        System.out.println(integers);
        Set<Integer> set = Set.of(1, 2, 3);
        Map<Integer, Integer> map = Map.of(1, 1, 2, 2, 3, 3);

        /*Path path = Paths.get("H.java");
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int run = javaCompiler.run(null, null, null, "-d",
            "/tmp/heapdump",path.toString());
        System.out.println(run);*/
    }

    // Left-Hand Operand Is Evaluated First
    public static void test1() {
        int i = 2;
        int j = (i = 3) * i;
        System.out.println(j);
    }

    // Implicit Left-Hand Operand In Operator Of Compound Assigment
    public static void test2() {
        int a = 9;
        a += (a = 3);
        System.out.println(a);
        int b = 9;
        b = b + (b = 3);
        System.out.println(b);
    }

    // Abrupt Completion of Evaluation of the Left-Hand Operand
    public static void test3() {
        int divisor = 0;
        try {
            int i = 1 / (divisor * loseBig());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Evaluation of Operands Before Operation
    static int loseBig() throws Exception {
        throw new Exception("Shuffle off to Buffalo!");
    }

    public static void test4() {
        Float x = 3.0f;
        Float y = NaN;

        System.out.println(!(x < y));
        // Don't rewrite this line to:
        System.out.println(x >= y);

        double d = 8E307;
        System.out.println(4.0 * d * 0.5);
        System.out.println(2.0 * d);
        // the first expression overflows and the second does not.
        assert (4.0 * d * 0.5 == 2.0 * d) == false;
    }

    // Evaluation Order At Method Invocation
    // Argument Lists are Evaluated Left-to-Right
    public static void test5() {
        String s = "going, ";
        // because the assignment of the string "gone" to s occurs after the first two arguments to
        // print3 have been evaluated.
        print3(s, s, s = "gone");
    }

    static void print3(String a, String b, String c) {
        System.out.println(a + b + c);
    }

    static int id;
    public static void test6() {
        try {
            // because the assignment of 3 to id is not executed.
            test(id = 1, oops(), id = 3);
        } catch (Exception e) {
            System.out.println(e + ", id=" + id);
        }
    }
    static int test(int a, int b, int c) {
        return a + b + c;
    }
    static int oops() throws Exception {
        throw new Exception("oops");
    }
}


