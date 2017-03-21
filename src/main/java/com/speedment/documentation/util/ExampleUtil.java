package com.speedment.documentation.util;

import com.speedment.datamodel.HaresApplication;
import com.speedment.datamodel.HaresApplicationBuilder;
import com.speedment.runtime.core.ApplicationBuilder.LogType;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author Per Minborg
 */
public final class ExampleUtil {

    private static final String DEFAULT_PASSWORD = "hare";

    public static HaresApplication buildApplication() {

        System.out.println("Connecting to a MySQL database on 'localhost:3306'");
        System.out.println("Enter password (<return> = '" + DEFAULT_PASSWORD + "'): ");

        final Scanner scan = new Scanner(System.in);
        final String inputPassword = scan.nextLine();
        final String password = inputPassword.isEmpty() ? DEFAULT_PASSWORD : inputPassword;

        final HaresApplicationBuilder builder = new HaresApplicationBuilder()
            .withLogging(LogType.STREAM)
            .withPassword(password);

        return builder.build();
    }

    public static <T> void printStream(Stream<T> stream) {
        stream.forEachOrdered(System.out::println);
    }

    public static void log(String testName) {
        System.out.format("*** Starting example \"%s\" ***%n", testName);
    }

    private ExampleUtil() {
    }

}
