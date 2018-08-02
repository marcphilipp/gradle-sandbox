import java.io.IOException;
import java.util.Arrays;

import static java.lang.ProcessBuilder.Redirect.INHERIT;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Arguments (" + args.length + "):");
        Arrays.stream(args).forEach(arg -> System.out.println("- '" + arg + "'"));
        System.out.println();
        System.out.println(">>> start");
        Process process = new ProcessBuilder()
                .command(args)
                .redirectOutput(INHERIT)
                .redirectError(INHERIT)
                .start();
        int exitCode = process.waitFor();
        System.out.println("<<< end");
        System.out.println();
        System.out.println("Exit code: " + exitCode);
    }
}
