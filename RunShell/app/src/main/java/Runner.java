
/**
 * Entry point of the program.
 * The first argument sets which program should run.
 *
 * <p>
 * This is currently implemented in Java as it seems there's no reliable way to override the name of the class that is generated in Kotlin,
 * using @JvmName doesn't appear to actually work for this purpose.
 * You would have to use the name of the class as RunnerKt, since this is only required as entrypoint, this is fine.
 */
public class Runner {

    public static void main(String... args) {
        EntryPointKt.start(args);
        System.out.println("exiting");
    }
}