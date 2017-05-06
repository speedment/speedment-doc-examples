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

import com.company.sakila.SakilaApplication;
import com.company.sakila.db0.sakila.film.Film;
import com.company.sakila.db0.sakila.film.FilmManager;
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

    private final SakilaApplication app;
    private final FilmManager films;

    public ComparablePredicates() {
        app = buildApplication();
        films = app.getOrThrow(FilmManager.class);
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

        films.stream()
            .forEachOrdered(System.out::println);

    }

    private void equal() {
        ExampleUtil.log("equal");

        long count = films.stream()
            .filter(Film.RATING.equal("PG-13"))
            .count();

        System.out.format("There are %d films(s) with a PG-13 rating %n", count);
    }

    private void notEqual() {
        ExampleUtil.log("notEqual");

        films.stream()
            .filter(Film.RATING.notEqual("PG-13"))
            .forEachOrdered(System.out::println);

    }

    private void lessThan() {
        ExampleUtil.log("lessThan");

        films.stream()
            .filter(Film.LENGTH.lessThan(120))
            .forEachOrdered(System.out::println);

    }

    private void lessOrEqual() {
        ExampleUtil.log("lessOrEqual");

        films.stream()
            .filter(Film.LENGTH.lessOrEqual(120))
            .forEachOrdered(System.out::println);

    }

    private void greaterThan() {
        ExampleUtil.log("greaterThan");

        films.stream()
            .filter(Film.LENGTH.greaterThan(120))
            .forEachOrdered(System.out::println);

    }

    private void greaterOrEqual() {
        ExampleUtil.log("greaterOrEqual");

        films.stream()
            .filter(Film.LENGTH.greaterOrEqual(120))
            .forEachOrdered(System.out::println);

    }

    private void between() {
        ExampleUtil.log("between");

        films.stream()
            .filter(Film.LENGTH.between(60, 120))
            .forEachOrdered(System.out::println);

    }

    private void betweenIncludeInclude() {
        ExampleUtil.log("betweenIncludeInclude");

        films.stream()
            .filter(Film.LENGTH.between(60, 120, Inclusion.START_INCLUSIVE_END_INCLUSIVE))
            .forEachOrdered(System.out::println);

    }

    private void notBetween() {
        ExampleUtil.log("notBetween");

        films.stream()
            .filter(Film.LENGTH.notBetween(60, 120))
            .forEachOrdered(System.out::println);

    }

    private void notBetweenIncludeInclude() {
        ExampleUtil.log("notBetweenIncludeInclude");

        films.stream()
            .filter(Film.LENGTH.notBetween(60, 120, Inclusion.START_INCLUSIVE_END_INCLUSIVE))
            .forEachOrdered(System.out::println);

    }

    private void in() {
        ExampleUtil.log("in");

        films.stream()
            .filter(Film.RATING.in("G", "PG", "PG-13"))
            .forEachOrdered(System.out::println);

    }

    private void notIn() {
        ExampleUtil.log("notIn");

        films.stream()
            .filter(Film.RATING.notIn("G", "PG", "PG-13"))
            .forEachOrdered(System.out::println);

    }

    private void inSet() {
        ExampleUtil.log("inSet");

        Set<String> set = Stream.of("G", "PG", "PG-13").collect(toSet());

        films.stream()
            .filter(Film.RATING.in(set))
            .forEachOrdered(System.out::println);

    }

    private void notInSet() {
        ExampleUtil.log("notInSet");

        Set<String> set = Stream.of("G", "PG", "PG-13").collect(toSet());

        films.stream()
            .filter(Film.RATING.notIn(set))
            .forEachOrdered(System.out::println);

    }

}
