/**
 * @see <a href="https://openjdk.org/jeps/358">JEP 358</a>
 * @see <a href="https://github.com/error0702/jvm-study/blob/main/src/autorun/jvm/jvmti/richNPE/richNPE.cpp">Rich NPE</a>
 * @see <a href="https://github.com/error0702/jvm-study/tree/main/src/autorun/jvm/jvmti#richnpe">How to Use </a>
 */
public class JEP358EnhancerNPE {
    public static void main(String[] args) {
        A a = new A();
        a.A().B().C();
    }

    static class A {
        public A A() {
            return this;
        }

        public A B() {
            return null;
        }

        public A C() {
            return this;
        }
    }
}
