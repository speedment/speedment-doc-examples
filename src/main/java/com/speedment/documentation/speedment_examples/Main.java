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
package com.speedment.documentation.speedment_examples;

import com.speedment.common.tuple.Tuple2;
import com.speedment.common.tuple.Tuples;
import com.speedment.datamodel.HaresApplication;
import com.speedment.datamodel.db0.hares.hare.Hare;
import com.speedment.datamodel.db0.hares.hare.HareManager;
import static com.speedment.documentation.util.ExampleUtil.buildApplication;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    private final HaresApplication app;
    private final HareManager hares;

    public Main() {
        app = buildApplication();
        hares = app.getOrThrow(HareManager.class);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        from();
        where();
        select();
    }

    private void from() {
        log("from");

        // Creates a stream over all hares in the database
        hares.stream()
            // For each hare in order, print the hare on standard out
            .forEachOrdered(System.out::println);

    }

    private void where() {
        log("where");

        // Searches are optimized in the background!
        final Optional<Hare> oldHare = hares.stream()
            .filter(Hare.AGE.greaterThan(5))
            .findAny();

        System.out.println(oldHare);
    }

    private void select() {
        log("select");

        // creates an `IntStream` consisting of the ages of all `Hare`s by
        // applying the Hare.AGE getter for each hare in the original stream
        final IntStream ages = hares.stream()
            .mapToInt(Hare.AGE.getter());

        // Print the ages
        ages.forEachOrdered(System.out::println);

        // Creates a stream of Tuples with two elements: id and Name
        Stream<Tuple2<Integer, String>> items = hares.stream()
            .map(h -> Tuples.of(h.getId(), h.getName()));

        // Print the id/name tuples
        items.forEachOrdered(System.out::println);
        
    }

    private void log(String testName) {

        System.out.format("*** Starting test \"%s\" ***%n", testName);
    }

}
