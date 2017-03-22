/**
 *
 * Copyright (c) 2006-2017, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.documentation.stream_fundamentals;

import com.speedment.documentation.util.ExampleUtil;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TerminalOperations {

    public static void main(String[] args) {
        forEach();
        forEachOrdered();
        collect();
        min();
        max();
        count();
        anyMatch();
        noneMatch();
        findFirst();
        findAny();
        toArray();
        toArrayWithSupplier();
        collectWith3Params();
        reduce();
        iterator();
        spliterator();
        sum();
        average();
        summaryStatistics();
    }

    private static void forEach() {
        ExampleUtil.log("forEach");
        Stream.of("B", "A", "C", "B")
            .forEach(System.out::print);

        System.out.println("");
    }

    private static void forEachOrdered() {
        ExampleUtil.log("forEachOrdered");
        Stream.of("B", "A", "C", "B")
            .forEachOrdered(System.out::print);

        System.out.println("");
    }

    private static void collect() {
        ExampleUtil.log("collect");
        System.out.println(
            Stream.of("B", "A", "C", "B")
                .collect(Collectors.toList())
        );

        System.out.println(
            Stream.of("B", "A", "C", "B")
                .collect(Collectors.toSet())
        );

        System.out.println(
            Stream.of("I", "am", "a", "stream")
                .collect(Collectors.toMap(
                    s -> s.toLowerCase(), // Key extractor
                    s -> s.length()) // Value extractor
                )
        );

    }

    private static void min() {
        ExampleUtil.log("min");
        System.out.println(
            Stream.of("B", "A", "C", "B")
                .min(String::compareTo)
        );
        System.out.println(
            Stream.<String>empty()
                .min(String::compareTo)
        );
    }

    private static void max() {
        ExampleUtil.log("max");
        System.out.println(
            Stream.of("B", "A", "C", "B")
                .max(String::compareTo)
        );
        System.out.println(
            Stream.<String>empty()
                .max(String::compareTo)
        );
    }

    private static void count() {
        ExampleUtil.log("count");
        System.out.println(
            Stream.of("B", "A", "C", "B")
                .count()
        );
        System.out.println(
            Stream.<String>empty()
                .count()
        );
    }

    private static void anyMatch() {
        ExampleUtil.log("anyMatch");
        System.out.println(
            Stream.of("B", "A", "C", "B")
                .anyMatch("A"::equals)
        );
        System.out.println(
            Stream.of("B", "A", "C", "B")
                .anyMatch("Z"::equals)
        );

    }

    private static void noneMatch() {
        ExampleUtil.log("noneMatch");
        System.out.println(
            Stream.of("B", "A", "C", "B")
                .noneMatch("A"::equals)
        );
        System.out.println(
            Stream.of("B", "A", "C", "B")
                .noneMatch("Z"::equals)
        );

    }

    private static void findFirst() {
        ExampleUtil.log("findFirst");
        System.out.println(
            Stream.of("B", "A", "C", "B")
                .findFirst()
        );
        System.out.println(
            Stream.<String>empty()
                .findFirst()
        );

    }

    private static void findAny() {
        ExampleUtil.log("findAny");
        System.out.println(
            Stream.of("B", "A", "C", "B")
                .findAny()
        );
        System.out.println(
            Stream.<String>empty()
                .findAny()
        );

    }

    private static void toArray() {
        ExampleUtil.log("toArray");
        System.out.println(
            Arrays.toString(
                Stream.of("B", "A", "C", "B")
                    .toArray()
            )
        );

    }

    private static void toArrayWithSupplier() {
        ExampleUtil.log("toArrayWithSuppler");
        System.out.println(
            Arrays.toString(
                Stream.of("B", "A", "C", "B")
                    .toArray(String[]::new)
            )
        );

    }

    private static void collectWith3Params() {
        ExampleUtil.log("collectWith3Params");

        System.out.println(
            Stream.of("B", "A", "C", "B")
                .collect(
                    () -> new StringBuilder(),
                    (sb0, sb1) -> sb0.append(sb1),
                    (sb0, sb1) -> sb0.append(sb1)
                )
                .toString()
        );

    }

    private static void reduce() {
        ExampleUtil.log("reduce");

        System.out.println(
            Stream.of(1, 2, 3, 4)
                .reduce((a, b) -> a + b)
        );

        System.out.println(
            Stream.of(1, 2, 3, 4)
                .reduce(100, (a, b) -> a + b)
        );

        System.out.println(
            Stream.of(1, 2, 3, 4)
                .parallel()
                .reduce(
                    0,
                    (a, b) -> a + b,
                    (a, b) -> a + b
                )
        );

    }

    private static void iterator() {
        ExampleUtil.log("iterator");

        Iterator<String> iterator
            = Stream.of("B", "A", "C", "B")
                .iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    private static void spliterator() {
        ExampleUtil.log("spliterator");

        Spliterator<String> spliterator
            = Stream.of("B", "A", "C", "B")
                .spliterator();

        spliterator.forEachRemaining(System.out::println);

    }

    private static void sum() {
        ExampleUtil.log("sum");

        System.out.println(
            IntStream.of(1, 2, 3, 4)
                .sum()
        );

    }

    private static void average() {
        ExampleUtil.log("average");

        System.out.println(
            IntStream.of(1, 2, 3, 4)
                .average()
        );

    }

    private static void summaryStatistics() {
        ExampleUtil.log("summaryStatistics");

        System.out.println(
            IntStream.of(1, 2, 3, 4)
                .summaryStatistics()
        );

        System.out.println(
            IntStream.empty()
                .summaryStatistics()
        );

    }

}
