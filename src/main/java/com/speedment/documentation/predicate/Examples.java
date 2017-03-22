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
package com.speedment.documentation.predicate;

import com.speedment.datamodel.HaresApplication;
import com.speedment.datamodel.db0.hares.hare.Hare;
import com.speedment.datamodel.db0.hares.hare.HareManager;
import static com.speedment.documentation.util.ExampleUtil.buildApplication;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Per Minborg
 */
public class Examples {

    private final HaresApplication app;
    private final HareManager hares;

    public Examples() {
        app = buildApplication();
        hares = app.getOrThrow(HareManager.class);
    }

    public static void main(String[] args) {
        new Examples().run();
    }

    private void run() {
        printFirstTen();
        startsWith();
        fieldTest();
        fieldTestUnOptimized();
    }

    private void printFirstTen() {
        hares.stream()
            .limit(10)
            .forEachOrdered(System.out::println);
    }

    private void startsWith() {
        Predicate<String> startsWithA = (String s) -> s.startsWith("A");

        Stream.of("Snail", "Ape", "Bird", "Ant", "Alligator")
            .filter(startsWithA)
            .forEachOrdered(System.out::println);

    }

    private void fieldTest() {
        Predicate<Hare> isOld = Hare.AGE.greaterThan(5);

        hares.stream()
            .filter(isOld)
            .forEachOrdered(System.out::println);
    }

    private void fieldTestUnOptimized() {
        Predicate<Hare> isOld = h -> h.getAge() > 5;

        hares.stream()
            .filter(isOld)
            .forEachOrdered(System.out::println);
    }

}
