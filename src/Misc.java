import java.util.List;
import java.util.Map;
import java.util.Set;

public class Misc {
    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3);
        Set<Integer> set = Set.of(1, 2, 3);
        Map<Integer, Integer> map = Map.of(1, 1, 2, 2, 3, 3);


        /*Path path = Paths.get("/Users/wangzheng/work/feature/src/H.java");
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

}


