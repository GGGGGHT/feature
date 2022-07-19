import java.util.Random;

import static java.util.Calendar.FRIDAY;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.SATURDAY;
import static java.util.Calendar.SUNDAY;
import static java.util.Calendar.THURSDAY;
import static java.util.Calendar.TUESDAY;
import static java.util.Calendar.WEDNESDAY;

/**
 * <a href="https://openjdk.org/jeps/325">Switch Expressions</a>
 */
public class JEP325SwitchExpressions {
    public static void main(String[] args) {

        var day = new Random().nextInt(8);
        /**
         * switch (day) {
         *     case MONDAY:
         *     case FRIDAY:
         *     case SUNDAY:
         *         System.out.println(6);
         *         break;
         *     case TUESDAY:
         *         System.out.println(7);
         *         break;
         *     case THURSDAY:
         *     case SATURDAY:
         *         System.out.println(8);
         *         break;
         *     case WEDNESDAY:
         *         System.out.println(9);
         *         break;
         * }
         */
        switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
            case TUESDAY -> System.out.println(7);
            case THURSDAY, SATURDAY -> System.out.println(8);
            case WEDNESDAY -> System.out.println(9);
        }

        var t = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> 9;
            default ->
            // if you want to return a value, you can use a block by
            {
                System.out.println("default");
                yield 0;
            }
        };

        System.out.println(t);

        var s = new Random().nextInt(2) == 0 ? "Foo" : "Bar";
        int result = switch (s) {
            case "Foo":
                yield 1;
            case "Bar":
                yield 2;
            default:
                System.out.println("Neither Foo nor Bar, hmmm...");
                yield 0;
        };
    }
}
