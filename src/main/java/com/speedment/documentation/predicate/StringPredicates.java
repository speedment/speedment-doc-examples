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

/**
 *
 * @author Per Minborg
 */
public class StringPredicates {

    private final SakilaApplication app;
    private final FilmManager films;

    public StringPredicates() {
        app = buildApplication();
        films = app.getOrThrow(FilmManager.class);
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

        long count = films.stream()
            .filter(Film.TITLE.isEmpty())
            .count();

        System.out.format("There are %d films(s) with an empty title %n", count);
    }

    private void isNotEmpty() {
        ExampleUtil.log("isNotEmpty");

        films.stream()
            .filter(Film.TITLE.isNotEmpty())
            .forEachOrdered(System.out::println);
    }

    private void equalIgnoreCase() {
        ExampleUtil.log("equalIgnoreCase");

        films.stream()
            .filter(Film.TITLE.equalIgnoreCase("AlABama dEVil"))
            .forEachOrdered(System.out::println);
    }

    private void notEqualIgnoreCase() {
        ExampleUtil.log("notEqualIgnoreCase");

        films.stream()
            .filter(Film.TITLE.notEqualIgnoreCase("AlABama dEVil"))
            .forEachOrdered(System.out::println);
    }

    private void startsWith() {
        ExampleUtil.log("startsWith");

        films.stream()
            .filter(Film.TITLE.startsWith("ALABAMA"))
            .forEachOrdered(System.out::println);
    }

    private void notStartsWith() {
        ExampleUtil.log("notStartsWith");

        films.stream()
            .filter(Film.TITLE.notStartsWith("ALABAMA"))
            .forEachOrdered(System.out::println);
    }

    private void startsWithIgnoreCase() {
        ExampleUtil.log("startsWithIgnoreCase");

        films.stream()
            .filter(Film.TITLE.startsWithIgnoreCase("ala"))
            .forEachOrdered(System.out::println);
    }

    private void notStartsWithIgnoreCase() {
        ExampleUtil.log("notStartsWithIgnoreCase");

        films.stream()
            .filter(Film.TITLE.notStartsWithIgnoreCase("ala"))
            .forEachOrdered(System.out::println);
    }

    private void endsWith() {
        ExampleUtil.log("endsWith");

        films.stream()
            .filter(Film.TITLE.endsWith("DEVIL"))
            .forEachOrdered(System.out::println);
    }

    private void notEndsWith() {
        ExampleUtil.log("notEndsWith");

        films.stream()
            .filter(Film.TITLE.notEndsWith("DEVIL"))
            .forEachOrdered(System.out::println);
    }

    private void endsWithIgnoreCase() {
        ExampleUtil.log("endsWithIgnoreCase");

        films.stream()
            .filter(Film.TITLE.endsWithIgnoreCase("deVIL"))
            .forEachOrdered(System.out::println);
    }

    private void notEndsWithIgnoreCase() {
        ExampleUtil.log("notEndsWithIgnoreCase");

        films.stream()
            .filter(Film.TITLE.notEndsWithIgnoreCase("deVIL"))
            .forEachOrdered(System.out::println);
    }

    private void contains() {
        ExampleUtil.log("contains");

        films.stream()
            .filter(Film.TITLE.contains("CON"))
            .forEachOrdered(System.out::println);
    }

    private void notContains() {
        ExampleUtil.log("notContains");

        films.stream()
            .filter(Film.TITLE.notContains("CON"))
            .forEachOrdered(System.out::println);
    }

    private void containsIgnoreCase() {
        ExampleUtil.log("containsIgnoreCase");

        films.stream()
            .filter(Film.TITLE.containsIgnoreCase("CoN"))
            .forEachOrdered(System.out::println);
    }

    private void notContainsIgnoreCase() {
        ExampleUtil.log("notContainsIgnoreCase");

        films.stream()
            .filter(Film.TITLE.notContainsIgnoreCase("CoN"))
            .forEachOrdered(System.out::println);
    }

    // startsWith("Olle").ignoreCase().negate() = notStartsWithIgnoreCase("Olle")
}
