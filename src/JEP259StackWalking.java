import java.util.stream.Collectors;

/**
 * @see <a href="https://openjdk.org/jeps/259">Stack-Walking</a>
 * @see <a href="https://github.com/spring-projects/spring-boot/issues/31665">Spring issue</a>
 * @see <a href="https://github.com/spring-projects/spring-boot/pull/31701">Spring Boot Stack-Walking</a>
 */
public class JEP259StackWalking {
	public static void a() {
		b();
	}
	public static void b() {
		c();
	}
	public static void c() {
			StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).walk((s) -> s.collect(Collectors.toList())).forEach(
				System.out::println);
	}
    public static void main(String[] args) {
		a();
    }
}
