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
package com.speedment.documentation.comparator;

import com.company.sakila.SakilaApplication;
import com.company.sakila.db0.sakila.film.Film;
import com.company.sakila.db0.sakila.film.FilmManager;
import com.speedment.documentation.util.ExampleUtil;
import static com.speedment.documentation.util.ExampleUtil.buildApplication;
import java.util.Comparator;
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
        simpleExample();
        sortByName();
        primitiveComparatorType();
    }

    private void printFirstTen() {
        ExampleUtil.log("printFirstTen");

        films.stream()
            .limit(10)
            .forEachOrdered(System.out::println);
    }

    private void unOptimized() {
        ExampleUtil.log("unOptimized");

        films.stream()
            .sorted(Comparator.comparing(Film::getTitle))
            .forEachOrdered(System.out::println);

    }

    private void simpleExample() {
        ExampleUtil.log("simpleExample");

        Comparator<String> naturalOrder = (String first, String second) -> first.compareTo(second);

        Stream.of("Snail", "Ape", "Bird", "Ant", "Alligator")
            .sorted(naturalOrder)
            .forEachOrdered(System.out::println);

    }

    private void sortByName() {
        ExampleUtil.log("sortByName");

        Comparator<Film> nameOrder = Film.TITLE.comparator();

        films.stream()
            .sorted(nameOrder)
            .forEachOrdered(System.out::println);

    }

    private void primitiveComparatorType() {
        ExampleUtil.log("primitiveComparatorType");

        Comparator<Film> idComparator = Film.FILM_ID.comparator();

        System.out.println(idComparator.getClass().getName());

    }

}
