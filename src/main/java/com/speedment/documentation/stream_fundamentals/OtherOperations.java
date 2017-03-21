package com.speedment.documentation.stream_fundamentals;

import com.speedment.documentation.util.ExampleUtil;
import java.util.stream.Stream;

public class OtherOperations {

    public static void main(String[] args) {
        isParallel();

    }

    private static void isParallel() {
        ExampleUtil.log("isParallel");

        System.out.println(
            Stream.of("B", "A", "C", "B")
                .parallel()
                .isParallel()
        );

        System.out.println(
            Stream.of("B", "A", "C", "B")
                .sequential()
                .isParallel()
        );

    }

    private static void close() {
        ExampleUtil.log("close");

        Stream<String> stream = Stream.of("B", "A", "C", "B");
        stream.forEachOrdered(System.out::println);
        stream.close();

        try (Stream<String> s = Stream.of("B", "A", "C", "B")) {
            s.forEachOrdered(System.out::println);
        }
        // The stream is autmatically closed when the stream goes out of scope.

    }

}
