import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;

/**
 * {@link java.lang.invoke.MethodHandles.Lookup} <br/>
 * @see MethodHandle
 * @see MethodHandles
 * <a href="http://openjdk.org/jeps/274">Enhanced Method Handles</a>
 * 反射的另一种使用方法
 */
public class JEP274EnhancedMethodHandles {
    public static void main(String[] args) throws Throwable {
        var lookup = MethodHandles.lookup();
        var methodType = MethodType.methodType(String.class, char.class, char.class);
        var methodHandle = lookup.findVirtual(String.class, "replace", methodType);
        var r = (String) methodHandle.invokeWithArguments(List.of("sappy", 'p', 'v'));
        System.out.println(r);

        var o = (String) methodHandle.invokeExact("happy", 'p', 's');
        System.out.println(o);

        var mt = MethodType.methodType(java.util.List.class, Object[].class);
        var mh = lookup.findStatic(java.util.Arrays.class, "asList", mt);
        var x = mh.invoke("one", "towo", "three");
        System.out.println(x);

        mt = MethodType.genericMethodType(3);
        var t = mh.asType(mt).invokeExact((Object) 1, (Object) 2, (Object) 3);
        System.out.println(t.equals(Arrays.asList(1, 2, 3)));


        mt = MethodType.methodType(int.class);
        mh = lookup.findVirtual(java.util.List.class, "size", mt);
        var i = (int) mh.invokeExact(java.util.Arrays.asList(1,2,3));
        System.out.println(i == 3);


        mt = MethodType.methodType(void.class, String.class);
        mh = lookup.findVirtual(java.io.PrintStream.class, "println", mt);
        mh.invokeExact(System.out, "Hello, world.");
    }
}
