import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// add annotation for record class
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) @interface GreaterThanZero {
}

@Target(ElementType.TYPE) @interface Test {
}

/**
 * <p>
 * record default final <br/>
 * nested record classes <br/>
 * define in method
 * <p>
 * <br/>
 * The abstract class java.lang.Record is the common superclass of all record classes.
 * <br/>
 * If exists multi class named "record", You might get a compiler error like this:
 * {@link com.myapp.Record}
 * {@link org.example.MyappPackageExample}
 * Both Record in the com.myapp package and Record in the java.lang package are imported with a wildcard. Consequently,
 * neither class takes precedence, and the compiler generates an error when it encounters the use of the simple name Record.
 * To enable this example to compile, change the import statement so that it imports the fully qualified name of Record
 *
 *
 * <p>
 * <h2>Two method about record class </h2>
 * {@link Class#isRecord} <br/>
 * {@link Class#getRecordComponents}
 * </p>
 */
public class JEP395Records {
    public static void main(String[] args) {
        Point1 p = new Point1(0, 2);
        p.y();
        p.x();
        System.out.println(p);
        System.out.println(Point1.goldenRatio);
        System.out.println(Point1.getGoldenRatio());
        System.out.println(p.sum());
        Class<? extends Point1> aClass = p.getClass();
        assert aClass.isRecord();
        Arrays.stream(aClass.getRecordComponents()).forEach(System.out::println);
    }
}

// Cannot inherit from final 'Point1'
// class ExtendPoint extends Point1 { }

class Point {
    private final int x;
    private final int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x() {
        return x;
    }

    int y() {
        return y;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override public String toString() {
        return "Point{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }
}

record Point1(int x, int y) {
    // You can declare static fields, static initializers, and static methods in a record class.
    // Static field
    static double goldenRatio;

    // Static initializer
    static {
        goldenRatio = (1 + Math.sqrt(5)) / 2;
    }

    public Point1 {
        if (x < 0) {
            throw new IllegalArgumentException("x must be >= 0");
        }
    }

    Point1() {
        this(0, 0);
    }

    static double getGoldenRatio() {
        return goldenRatio;
    }

    public int x() {
        System.out.println("Point1#x");
        return x;
    }

    /*
    Field declarations must be static:
    BiFunction<Double, Double, Double> diagonal;

    // Instance initializers are not allowed in records:
    {
        diagonal = (x, y) -> Math.sqrt(x*x + y*y);
    }
    */
    public int sum() {
        return x + y;
    }

    // Nested record class
    record RotationAngle(double angle) {
        public RotationAngle {
            angle = Math.toRadians(angle);
        }
    }
}

// generic record class
record Triangle<C extends Point>(C top, C left, C right) {
}

// implement interface
record Point2(int x, int y) implements Comparable<Point2>, Runnable {
    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override public int compareTo(Point2 o) {
        return Integer.compare(x, o.x);
    }

    @Override public void run() {
        System.out.println("Point2#run");
    }
}

@Test
record Rectangle1(
    @GreaterThanZero double length,
    @GreaterThanZero double width) {
}

record Merchant(String name) {
}

record Sale(Merchant merchant, LocalDate date, double value) {
}

class MerchantExample {

    public static void main(String[] args) {

        Merchant sneha = new Merchant("Sneha");
        Merchant raj = new Merchant("Raj");
        Merchant florence = new Merchant("Florence");
        Merchant leo = new Merchant("Leo");

        List<Merchant> merchantList = List.of(sneha, raj, florence, leo);

        List<Sale> salesList = List.of(
            new Sale(sneha, LocalDate.of(2020, Month.NOVEMBER, 13), 11034.20),
            new Sale(raj, LocalDate.of(2020, Month.NOVEMBER, 20), 8234.23),
            new Sale(florence, LocalDate.of(2020, Month.NOVEMBER, 19), 10003.67),
            // ...
            new Sale(leo, LocalDate.of(2020, Month.NOVEMBER, 4), 9645.34));

        MerchantExample app = new MerchantExample();

        List<Merchant> topMerchants =
            app.findTopMerchants(salesList, merchantList, 2020, Month.NOVEMBER);
        System.out.println("Top merchants: ");
        topMerchants.forEach(m -> System.out.println(m.name()));
    }

    List<Merchant> findTopMerchants(
        List<Sale> sales, List<Merchant> merchants, int year, Month month) {

        // Local record class
        record MerchantSales(Merchant merchant, double sales) {
        }

        return merchants.stream()
            .map(merchant -> new MerchantSales(
                merchant, this.computeSales(sales, merchant, year, month)))
            .sorted((m1, m2) -> Double.compare(m2.sales(), m1.sales()))
            .map(MerchantSales::merchant)
            .collect(Collectors.toList());
    }

    double computeSales(List<Sale> sales, Merchant mt, int yr, Month mo) {
        return sales.stream()
            .filter(s -> s.merchant().name().equals(mt.name()) &&
                s.date().getYear() == yr &&
                s.date().getMonth() == mo)
            .mapToDouble(Sale::value)
            .sum();
    }
}

class ContactList {

    public static void main(String[] args) {

        class Task implements Runnable {

            // Record class member, implicitly static,
            // declared in an inner class
            Contact c;

            public Task(Contact contact) {
                c = contact;
            }

            public void run() {
                System.out.println(c.name + ", " + c.number);
            }
        }

        List<Contact> contacts = List.of(
            new Contact("Sneha", "555-1234"),
            new Contact("Raj", "555-2345"));
        contacts
            .forEach(cont -> new Thread(new Task(cont)).start());
    }

    record Contact(String name, String number) {
    }
}