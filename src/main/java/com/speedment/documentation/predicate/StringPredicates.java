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
import com.speedment.documentation.util.ExampleUtil;
import static com.speedment.documentation.util.ExampleUtil.buildApplication;

/**
 *
 * @author Per Minborg
 */
public class StringPredicates {

    private final HaresApplication app;
    private final HareManager hares;

    public StringPredicates() {
        app = buildApplication();
        hares = app.getOrThrow(HareManager.class);
    }

    public static void main(String[] args) {
        new StringPredicates().run();
    }

    private void run() {
        isEmpty();
        isNotEmpty();
        equalIgnoreCase();
        notEqualIgnoreCase();
        startsWith();
        notStartsWith();
        startsWithIgnoreCase();
        notStartsWithIgnoreCase();
        endsWith();
        notEndsWith();
        endsWithIgnoreCase();
        notEndsWithIgnoreCase();
        contains();
        notContains();
        containsIgnoreCase();
        notContainsIgnoreCase();
    }

    private void isEmpty() {
        ExampleUtil.log("isEmpty");

        long count = hares.stream()
            .filter(Hare.NAME.isEmpty())
            .count();

        System.out.format("There are %d hare(s) with an empty name %n", count);
    }

    private void isNotEmpty() {
        ExampleUtil.log("isNotEmpty");

        hares.stream()
            .filter(Hare.NAME.isNotEmpty())
            .forEachOrdered(System.out::println);
    }

    private void equalIgnoreCase() {
        ExampleUtil.log("equalIgnoreCase");

        hares.stream()
            .filter(Hare.NAME.equalIgnoreCase("HaRry"))
            .forEachOrdered(System.out::println);
    }

    private void notEqualIgnoreCase() {
        ExampleUtil.log("notEqualIgnoreCase");

        hares.stream()
            .filter(Hare.NAME.notEqualIgnoreCase("HaRry"))
            .forEachOrdered(System.out::println);
    }

    private void startsWith() {
        ExampleUtil.log("startsWith");

        hares.stream()
            .filter(Hare.NAME.startsWith("H"))
            .forEachOrdered(System.out::println);
    }

    private void notStartsWith() {
        ExampleUtil.log("notStartsWith");

        hares.stream()
            .filter(Hare.NAME.notStartsWith("H"))
            .forEachOrdered(System.out::println);
    }

    private void startsWithIgnoreCase() {
        ExampleUtil.log("startsWithIgnoreCase");

        hares.stream()
            .filter(Hare.NAME.startsWithIgnoreCase("he"))
            .forEachOrdered(System.out::println);
    }

    private void notStartsWithIgnoreCase() {
        ExampleUtil.log("notStartsWithIgnoreCase");

        hares.stream()
            .filter(Hare.NAME.notStartsWithIgnoreCase("he"))
            .forEachOrdered(System.out::println);
    }

    private void endsWith() {
        ExampleUtil.log("endsWith");

        hares.stream()
            .filter(Hare.NAME.endsWith("y"))
            .forEachOrdered(System.out::println);
    }

    private void notEndsWith() {
        ExampleUtil.log("notEndsWith");

        hares.stream()
            .filter(Hare.NAME.notEndsWith("y"))
            .forEachOrdered(System.out::println);
    }

    private void endsWithIgnoreCase() {
        ExampleUtil.log("endsWithIgnoreCase");

        hares.stream()
            .filter(Hare.NAME.endsWithIgnoreCase("Y"))
            .forEachOrdered(System.out::println);
    }

    private void notEndsWithIgnoreCase() {
        ExampleUtil.log("notEndsWithIgnoreCase");

        hares.stream()
            .filter(Hare.NAME.notEndsWithIgnoreCase("Y"))
            .forEachOrdered(System.out::println);
    }

    private void contains() {
        ExampleUtil.log("contains");

        hares.stream()
            .filter(Hare.NAME.contains("tt"))
            .forEachOrdered(System.out::println);
    }

    private void notContains() {
        ExampleUtil.log("notContains");

        hares.stream()
            .filter(Hare.NAME.notContains("tt"))
            .forEachOrdered(System.out::println);
    }

    private void containsIgnoreCase() {
        ExampleUtil.log("containsIgnoreCase");

        hares.stream()
            .filter(Hare.NAME.containsIgnoreCase("Tt"))
            .forEachOrdered(System.out::println);
    }

    private void notContainsIgnoreCase() {
        ExampleUtil.log("notContainsIgnoreCase");

        hares.stream()
            .filter(Hare.NAME.notContainsIgnoreCase("Tt"))
            .forEachOrdered(System.out::println);
    }

    // startsWith("Olle").ignoreCase().negate() = notStartsWithIgnoreCase("Olle")
}
