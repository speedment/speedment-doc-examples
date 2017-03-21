package com.speedment.documentation.stream_fundamentals;

import com.speedment.documentation.util.ExampleUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntermediateOperations {

    public static void main(String[] args) {
        filter();
        map();
        distinct();
        sorted();
        sortedReversedOrder();
        limit();
        skip();
        flatMap();
        flatMapLists();
        peek();
        parallel();
        sequential();
        unordered();
        onClose();
        mapToInt();
        mapToLong();
        mapToDouble();
        flatMapToInt();
        boxed();
        asLongStream();
        asDoubleStream();
    }

    private static void filter() {
        ExampleUtil.log("filter");
        Stream.of("B", "A", "C", "B")
            .filter(s -> s.equals("B"))
            .forEachOrdered(System.out::println);
    }

    private static void map() {
        ExampleUtil.log("map");
        Stream.of("B", "A", "C", "B")
            .map(s -> s.toLowerCase())
            .forEachOrdered(System.out::println);
    }

    private static void distinct() {
        ExampleUtil.log("distinct");
        Stream.of("B", "A", "C", "B")
            .distinct()
            .forEachOrdered(System.out::println);
    }

    private static void sorted() {
        ExampleUtil.log("sorted");
        Stream.of("B", "A", "C", "B")
            .sorted()
            .forEachOrdered(System.out::println);
    }

    private static void sortedReversedOrder() {
        ExampleUtil.log("sortedReversedOrder");
        Stream.of("B", "A", "C", "B")
            .sorted(Comparator.reverseOrder())
            .forEachOrdered(System.out::println);
    }

    private static void limit() {
        ExampleUtil.log("limit");
        Stream.of("B", "A", "C", "B")
            .limit(2)
            .forEachOrdered(System.out::println);
    }

    private static void skip() {
        ExampleUtil.log("skip");
        Stream.of("B", "A", "C", "B")
            .skip(1)
            .forEachOrdered(System.out::println);
    }

    private static void flatMap() {
        ExampleUtil.log("flatMap");
        Stream.of(
            Stream.of("B", "A"),
            Stream.of("C", "B")
        )
            .flatMap(Function.identity())
            .forEachOrdered(System.out::println);
    }

    private static void flatMapLists() {
        ExampleUtil.log("flatMapLists");
        Stream.of(
            Arrays.asList("B", "A"),
            Arrays.asList("C", "B")
        )
            .flatMap(l -> l.stream())
            .forEachOrdered(System.out::println);
    }

    private static void peek() {
        ExampleUtil.log("peek");
        long count = Stream.of("B", "A", "C", "B")
            .peek(System.out::print)
            .count();
        System.out.println("There are " + count + " elements in the stream");
    }

    private static void parallel() {
        ExampleUtil.log("parallel");
        Set<String> threads = Collections.newSetFromMap(new ConcurrentHashMap<>());
        Stream.of("B", "A", "C", "B")
            .parallel()
            .peek(e -> threads.add(Thread.currentThread().getName()))
            .forEachOrdered(System.out::println);
        System.out.format("These threads executed the stream %s %n", threads.toString());
    }

    private static void sequential() {
        ExampleUtil.log("sequential");
        Set<String> threads = Collections.newSetFromMap(new ConcurrentHashMap<>());
        Stream.of("B", "A", "C", "B")
            .parallel()
            .sequential()
            .peek(e -> threads.add(Thread.currentThread().getName()))
            .forEachOrdered(System.out::println);
        System.out.format("These threads executed the stream %s %n", threads.toString());
    }

    private static void unordered() {
        ExampleUtil.log("unordered");
        Stream.of("B", "A", "C", "B")
            .unordered()
            .forEachOrdered(System.out::println);
    }

    private static void onClose() {
        ExampleUtil.log("onClose");
        Stream<String> stream = Stream.of("B", "A", "C", "B")
            .onClose(() -> System.out.println("The Stream was closed"));
        stream.forEachOrdered(System.out::println);
        stream.close();
    }

    private static void mapToInt() {
        ExampleUtil.log("mapToInt");
        Stream.of("B", "A", "C", "B")
            .mapToInt(s -> s.hashCode())
            .forEachOrdered(System.out::println);
    }

    private static void mapToLong() {
        ExampleUtil.log("mapToLong");
        Stream.of("B", "A", "C", "B")
            .mapToLong(s -> s.hashCode() * 1_000_000_000_000l)
            .forEachOrdered(System.out::println);
    }

    private static void mapToDouble() {
        ExampleUtil.log("mapToDouble");
        Stream.of("B", "A", "C", "B")
            .mapToDouble(s -> s.hashCode() / 10.0)
            .forEachOrdered(System.out::println);
    }

    private static void flatMapToInt() {
        ExampleUtil.log("flatMapToInt");
        Stream.of(
            IntStream.of(1, 2),
            IntStream.of(3, 4)
        )
            .flatMapToInt(s -> s.map(i -> i + 1))
            .forEachOrdered(System.out::println);
    }

    private static void boxed() {
        ExampleUtil.log("boxed");
        IntStream.of(1, 2, 3, 4)
            .boxed()
            .map(i -> i.getClass().getSimpleName() + "(" + i + ") ")
            .forEachOrdered(System.out::println);
    }

    private static void asLongStream() {
        ExampleUtil.log("asLongStream");
        IntStream.of(1, 2, 3, 4)
            .asLongStream()
            .forEachOrdered(System.out::println);
    }

    private static void asDoubleStream() {
        ExampleUtil.log("asDoubleStream");
        IntStream.of(1, 2, 3, 4)
            .asDoubleStream()
            .forEachOrdered(System.out::println);

    }

}
