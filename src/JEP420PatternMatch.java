import java.util.function.Function;

interface Shape_1 {
}

/**
 * <b>instanceof</b> matches the type of the instance. <br/>
 * <b>switch expression</b>
 */
public class JEP420PatternMatch {
    public static double withOutInstanceOf(Shape_1 shape) throws IllegalArgumentException {
        if (shape instanceof Rectangle_1) {
            var r = ((Rectangle_1) shape);
            return 2 * r.length() + 2 * r.width();
        } else if (shape instanceof Circle_1) {
            var c = (Circle_1) shape;
            return 2 * c.radius() * Math.PI;
        } else {
            throw new IllegalArgumentException("Unrecognized shape");
        }
    }

    public static double withInstanceOf(Shape_1 shape) throws IllegalArgumentException {
        if (shape instanceof Rectangle_1 r) {
            return 2 * r.length() + 2 * r.width();
        } else if (shape instanceof Circle_1 c) {
            return 2 * c.radius() * Math.PI;
        } else {
            throw new IllegalArgumentException("Unrecognized shape");
        }
    }

    public static double switchExpressionAndInstanceOf(Shape_1 shape)
        throws IllegalArgumentException {
        return switch (shape) {
            case Rectangle_1 r -> 2 * r.length() + 2 * r.width();
            case Circle_1 c -> 2 * c.radius() * Math.PI;
            default -> throw new IllegalArgumentException("Unrecognized shape");
        };
    }

    // TAG: preview
    static void boolExpressionWithOutSwitch(Object obj) {
        switch (obj) {
            case String s:
                if (s.length() == 1) {
                    System.out.println("Short: " + s);
                } else {
                    System.out.println(s);
                }
                break;
            default:
                System.out.println("Not a string");
        }
    }

    static void boolExpressionWithSwitch(Object obj) {
        switch (obj) {
            case String s && (s.length() == 1) -> System.out.println("Short: " + s);
            case String s -> System.out.println(s);
            default -> System.out.println("Not a string");
        }
    }


    static Function<Integer, String> withOutBrackets(Object obj) {
        boolean b = true;
        return switch (obj) {
            case String s && b -> t -> s;
            default            -> t -> "Default string";
        };
    }

    static Function<Integer, String> withBrackets(Object obj) {
        boolean b = true;
        return switch (obj) {
            case (String s && b) -> t -> s;
            default              -> t -> "Default string";
        };
    }
    // TAG: preview
}

record Rectangle_1(double length, double width) implements Shape_1 {
}

record Circle_1(double radius) implements Shape_1 {
}
