package example;

/**
 * Says hello.
 *
 * @since 1.0
 */
public final class Hello {

    private Hello() {
    }

    /**
     * Returns a greeting.
     *
     * @return the greeting
     * @since 2.0
     */
    public static String greet() {
        return "Hello";
    }
}
