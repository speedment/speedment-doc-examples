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
import com.speedment.runtime.field.predicate.Inclusion;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import java.util.stream.Stream;

/**
 *
 * @author Per Minborg
 */
public class ComparablePredicates {

    private final HaresApplication app;
    private final HareManager hares;

    public ComparablePredicates() {
        app = buildApplication();
        hares = app.getOrThrow(HareManager.class);
    }

    public static void main(String[] args) {
        new ComparablePredicates().run();
    }

    private void run() {
        all();
        equal();
        notEqual();
        lessThan();
        lessOrEqual();
        greaterThan();
        greaterOrEqual();
        between();
        betweenIncludeInclude();
        notBetween();
        notBetweenIncludeInclude();
        in();
        notIn();
        inSet();
        notInSet();
    }

    private void all() {
        ExampleUtil.log("all");

        hares.stream()
            .forEachOrdered(System.out::println);

    }

    private void equal() {
        ExampleUtil.log("equal");

        long count = hares.stream()
            .filter(Hare.AGE.equal(3))
            .count();

        System.out.format("There are %d hare(s) with an age of 3 %n", count);
    }

    private void notEqual() {
        ExampleUtil.log("notEqual");

        hares.stream()
            .filter(Hare.AGE.notEqual(3))
            .forEachOrdered(System.out::println);

    }

    private void lessThan() {
        ExampleUtil.log("lessThan");

        hares.stream()
            .filter(Hare.AGE.lessThan(3))
            .forEachOrdered(System.out::println);

    }

    private void lessOrEqual() {
        ExampleUtil.log("lessOrEqual");

        hares.stream()
            .filter(Hare.AGE.lessOrEqual(3))
            .forEachOrdered(System.out::println);

    }

    private void greaterThan() {
        ExampleUtil.log("greaterThan");

        hares.stream()
            .filter(Hare.AGE.greaterThan(3))
            .forEachOrdered(System.out::println);

    }

    private void greaterOrEqual() {
        ExampleUtil.log("greaterOrEqual");

        hares.stream()
            .filter(Hare.AGE.greaterOrEqual(3))
            .forEachOrdered(System.out::println);

    }

    private void between() {
        ExampleUtil.log("between");

        hares.stream()
            .filter(Hare.AGE.between(3, 9))
            .forEachOrdered(System.out::println);

    }

    private void betweenIncludeInclude() {
        ExampleUtil.log("betweenIncludeInclude");

        hares.stream()
            .filter(Hare.AGE.between(3, 9, Inclusion.START_INCLUSIVE_END_INCLUSIVE))
            .forEachOrdered(System.out::println);

    }

    private void notBetween() {
        ExampleUtil.log("notBetween");

        hares.stream()
            .filter(Hare.AGE.notBetween(3, 9))
            .forEachOrdered(System.out::println);

    }

    private void notBetweenIncludeInclude() {
        ExampleUtil.log("notBetweenIncludeInclude");

        hares.stream()
            .filter(Hare.AGE.notBetween(3, 9, Inclusion.START_INCLUSIVE_END_INCLUSIVE))
            .forEachOrdered(System.out::println);

    }

    private void in() {
        ExampleUtil.log("in");

        hares.stream()
            .filter(Hare.AGE.in(2, 3, 4))
            .forEachOrdered(System.out::println);

    }

    private void notIn() {
        ExampleUtil.log("notIn");

        hares.stream()
            .filter(Hare.AGE.notIn(2, 3, 4))
            .forEachOrdered(System.out::println);

    }

    private void inSet() {
        ExampleUtil.log("inSet");

        Set<Integer> set = Stream.of(2, 3, 4).collect(toSet());

        hares.stream()
            .filter(Hare.AGE.in(set))
            .forEachOrdered(System.out::println);

    }

    private void notInSet() {
        ExampleUtil.log("notInSet");

        Set<Integer> set = Stream.of(2, 3, 4).collect(toSet());

        hares.stream()
            .filter(Hare.AGE.notIn(set))
            .forEachOrdered(System.out::println);

    }

}
