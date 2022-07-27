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

        // case label_1, label_2, ..., label_n -> expression;|throw-statement;|block
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
                // return 0;
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
                throw new IllegalStateException("Invalid day: " + day);
        };

        // A switch expression must either complete normally with a value or complete abruptly by throwing an exception.
        /*int i = switch (day) {
            case MONDAY -> {
                System.out.println("Monday");
                // ERROR! Block doesn't contain a yield statement
                // yield 0;
            }
            default -> 1;
        };*/

        /*var m = switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY:
                yield 0;
            default:
                System.out.println("Second half of the week");
                // ERROR! Group doesn't contain a yield statement
        };*/

       /* var e = new Random().nextInt(2_000_000);
        z:
        for (int i = 0; i < MAX_VALUE; ++i) {
            // Because a switch expression must evaluate to a single value (or throw an exception),
            // you can't jump through a switch expression with a break, yield, return, or continue statement
            int k = switch (e) {
                case 0:
                    // yield 1;
                    break;
                case 1:
                    // yield 2;
                    return;
                default:
                    continue z;
                    // ERROR! Illegal jump through a switch expression
            };
        }*/
    }

    /**
     *
     * use switch expression must list all the cases.
     */
    static void switchStatementExhaustive(Object obj) {
        /*switch (obj) {  // error: the switch statement does not cover
            //        all possible input values
            case String s -> System.out.println(s);
            case Integer i -> System.out.println("Integer");
            // default -> System.out.println("Default");
        }*/
    }
}
