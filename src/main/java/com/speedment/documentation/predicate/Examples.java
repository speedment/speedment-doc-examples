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
import java.sql.Date;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Per Minborg
 */
public class Examples {

    private final SakilaApplication app;
    private final FilmManager films;

    public Examples() {
        app = buildApplication();
        films = app.getOrThrow(FilmManager.class);
    }

    public static void main(String[] args) {
        new Examples().run();
    }

    private void run() {
        printFirstTen();
        startsWith();
        fieldTest();
        fieldTestUnOptimized();
        compositeExample();
    }

    private void printFirstTen() {
        ExampleUtil.log("printFirstTen");

        films.stream()
            .limit(10)
            .forEachOrdered(System.out::println);
    }

    private void startsWith() {
        ExampleUtil.log("startsWith");

        Predicate<String> startsWithA = (String s) -> s.startsWith("A");

        Stream.of("Snail", "Ape", "Bird", "Ant", "Alligator")
            .filter(startsWithA)
            .forEachOrdered(System.out::println);

    }

    private void fieldTest() {
        ExampleUtil.log("fieldTest");

        Predicate<Film> startsWithA = Film.TITLE.startsWith("A");

        films.stream()
            .filter(startsWithA)
            .forEachOrdered(System.out::println);
    }

    private void fieldTestUnOptimized() {
        ExampleUtil.log("fieldTestUnOptimized");

        Predicate<Film> startsWithA = f -> f.getTitle().startsWith("A");

        films.stream()
            .filter(startsWithA)
            .forEachOrdered(System.out::println);
    }

    private void compositeExample() {
        ExampleUtil.log("compositeExample");

        films.stream()
            .filter(Film.RATING.in("G", "PG"))
            .filter(Film.LENGTH.greaterThan(120))
            .filter(Film.SPECIAL_FEATURES.contains("Commentaries"))
            .forEachOrdered(System.out::println);
    }

}
