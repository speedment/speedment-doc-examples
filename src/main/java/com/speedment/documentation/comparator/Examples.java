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
import com.company.sakila.db0.sakila.actor.Actor;
import com.company.sakila.db0.sakila.actor.ActorManager;
import com.company.sakila.db0.sakila.address.Address;
import com.company.sakila.db0.sakila.address.AddressManager;
import com.company.sakila.db0.sakila.film.Film;
import com.company.sakila.db0.sakila.film.FilmManager;
import com.company.sakila.db0.sakila.film.generated.GeneratedFilm;
import com.company.sakila.db0.sakila.film_actor.FilmActor;
import com.company.sakila.db0.sakila.film_actor.FilmActorManager;
import com.speedment.documentation.util.ExampleUtil;
import static com.speedment.documentation.util.ExampleUtil.buildApplication;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import java.util.stream.Stream;

/**
 *
 * @author Per Minborg
 */
public class Examples {

    private final SakilaApplication app;
    private final FilmManager films;
    private final AddressManager addresses;
    private final ActorManager actors;
    private final FilmActorManager filmActors;

    public Examples() {
        app = buildApplication();
        films = app.getOrThrow(FilmManager.class);
        addresses = app.getOrThrow(AddressManager.class);
        actors = app.getOrThrow(ActorManager.class);
        filmActors = app.getOrThrow(FilmActorManager.class);
    }

    public static void main(String[] args) {
        new Examples().run();
    }

    private void run() {
        printFirstTen();
        simpleExample();
        sortByTitle();
        sortByTitleReversed();
        sortByAddress2NullsFirst();
        sortByAddress2NullsFirstReversed();
        sortByAddress2NullsLast();
        sortByAddress2NullsLastReversed();
        primitiveComparatorType();
        sortByLengthReversedAndThenTitle();
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

    private void sortByTitle() {
        ExampleUtil.log("sortByTitle");

        films.stream()
            .sorted(Film.TITLE.comparator())
            .forEachOrdered(System.out::println);

    }

    private void sortByTitleReversed() {
        ExampleUtil.log("sortByTitleReversed");

        films.stream()
            .sorted(Film.TITLE.comparator().reversed())
            .forEachOrdered(System.out::println);

    }

    private void sortByAddress2NullsFirst() {
        ExampleUtil.log("sortByAddress2NullsFirst");

        addresses.stream()
            .sorted(Address.ADDRESS2.comparatorNullFieldsFirst())
            .forEachOrdered(System.out::println);

    }

    private void sortByAddress2NullsFirstReversed() {
        ExampleUtil.log("sortByAddress2NullsFirstReversed");

        addresses.stream()
            .sorted(Address.ADDRESS2.comparatorNullFieldsFirst().reversed())
            .forEachOrdered(System.out::println);

    }

    private void sortByAddress2NullsLast() {
        ExampleUtil.log("sortByAddress2NullsLast");

        addresses.stream()
            .sorted(Address.ADDRESS2.comparatorNullFieldsLast())
            .forEachOrdered(System.out::println);

    }
    
    private void sortByAddress2NullsLastReversed() {
        ExampleUtil.log("sortByAddress2NullsLastReversed");

        addresses.stream()
            .sorted(Address.ADDRESS2.comparatorNullFieldsLast().reversed())
            .forEachOrdered(System.out::println);

    }

    private void sortByLengthReversedAndThenTitle() {
        ExampleUtil.log("sortByLengthReversedAndThenTitle");

        films.stream()
            .sorted(Film.TITLE.comparator()) // <-- Second order
            .sorted(Film.RATING.comparator().reversed()) // <-- First order
            .forEachOrdered(System.out::println);
    }

    private void primitiveComparatorType() {
        ExampleUtil.log("primitiveComparatorType");

        Comparator<Film> idComparator = Film.FILM_ID.comparator();

        System.out.println(idComparator.getClass().getName());

    }
    
}
