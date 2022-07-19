import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * @see VarHandle
 * <a href="https://openjdk.org/jeps/193>Variable Handles</a>
 */
public class JEP193VariableHandles {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        VarHandle v = MethodHandles.lookup().findStaticVarHandle(Int.class, "value", Integer.class);
        v.compareAndExchange(null, 1);
        System.out.println(Int.value);
        v.compareAndSet(1, 2);
        System.out.println(Int.value == 2);

        VarHandle v1 = MethodHandles.privateLookupIn(Int.class, MethodHandles.lookup())
            .findVarHandle(Int.class, "v", Integer.class);
        var i = new Int();
        v1.compareAndSet(i, null, 10);
        System.out.println(i.getV() == 10);

        VarHandle arr = MethodHandles.lookup().in(Int.class).findVarHandle(Int.class, "arr", int[].class);
        arr.compareAndSet(i, null, new int[]{4, 5, 6});
        System.out.println(i.arr[1] == 5);
        System.out.println(arr.varType());
        System.out.println(arr.coordinateTypes());
    }
}

class Int {
    public static Integer value;
    public int[] arr;

    private Integer v;

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
