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
import java.util.stream.Stream;

public class OtherOperations {

    public static void main(String[] args) {
        isParallel();
        close();
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

        Runnable closePrinter = () -> System.out.println("The Stream was closed");

        Stream<String> stream = Stream.of("B", "A", "C", "B");
        stream
            .onClose(closePrinter)
            .forEachOrdered(System.out::println);
        stream.close();

        try (Stream<String> s = Stream.of("B", "A", "C", "B").onClose(closePrinter)) {
            s.forEachOrdered(System.out::println);
        }
        // The stream is autmatically closed when the stream goes out of scope.

        try {

            // The stream is autmatically closed even on exceptions
            try (Stream<String> s = Stream.of("B", "A", "C", "B").onClose(closePrinter)) {
                String value = null;
                value.length(); // Will throw a NullPointerException
                s.forEachOrdered(System.out::println);
            }
            // But the stream is closed anyhow
            
        } catch (NullPointerException npe) {
            System.out.println("A NullPointerException was thrown");
        }

    }

}
