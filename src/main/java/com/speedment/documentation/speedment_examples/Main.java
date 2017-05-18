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

import com.company.sakila.SakilaApplication;
import com.company.sakila.db0.sakila.actor.Actor;
import com.company.sakila.db0.sakila.actor.ActorManager;
import com.company.sakila.db0.sakila.film.Film;
import com.company.sakila.db0.sakila.film.FilmManager;
import com.company.sakila.db0.sakila.film_actor.FilmActor;
import com.company.sakila.db0.sakila.film_actor.FilmActorManager;
import com.company.sakila.db0.sakila.language.Language;
import com.company.sakila.db0.sakila.language.LanguageManager;
import com.speedment.common.mapstream.MapStream;
import com.speedment.common.tuple.Tuple2;
import com.speedment.common.tuple.Tuples;
import static com.speedment.documentation.util.ExampleUtil.buildApplication;
import com.speedment.runtime.core.ApplicationBuilder.LogType;
import com.speedment.runtime.core.util.StreamComposition;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    private final SakilaApplication app;
    private final FilmManager films;
    private final LanguageManager languages;
    private final ActorManager actors;
    private final FilmActorManager filmActors;

    public Main() {
        app = buildApplication(
            b -> b
//                .withBundle(DataStoreBundle.class)
                .withLogging(LogType.APPLICATION_BUILDER)
        );
        films = app.getOrThrow(FilmManager.class);
        languages = app.getOrThrow(LanguageManager.class);
        actors = app.getOrThrow(ActorManager.class);
        filmActors = app.getOrThrow(FilmActorManager.class);

//        ExecutorService myExecutorService = Executors.newFixedThreadPool(3);
//        DataStoreComponent dsc = app.getOrThrow(DataStoreComponent.class);
//        dsc.load(myExecutorService);
//
//        app.get(DataStoreComponent.class)
//            .map(DataStoreComponent::getStatistics)
//            .map(StatisticsUtil::prettyPrint)
//            .ifPresent(s -> s.forEachOrdered(System.out::println));

        //app.get(DataStoreComponent.class).ifPresent(DataStoreComponent::load);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        from();
        where();
        select();
        groupBy();
        having();
        distinct();
        orderBy();
        offset();
        limit();
        offsetLimit();
        count();
        // Show page 2 (zero is first page) of Films order by title desc
        getPage(2, Film.TITLE.comparator().reversed());
        join();
        partitionedBy();
        unionAll();
        union();
        oneToMany();
        manyToOne();
        manyToMany();
        pivot();
    }

    private void from() {
        log("from");

        // Creates a stream over all hares in the database
        films.stream()
            // For each hare in order, print the hare on standard out
            .forEachOrdered(System.out::println);

    }

    private void where() {
        log("where");

        // Searches are optimized in the background!
        final Optional<Film> longFilm = films.stream()
            .filter(Film.LENGTH.greaterThan(120))
            .findAny();

        System.out.println(longFilm);
    }

    private void select() {
        log("select");

        // creates an `IntStream` consisting of the ids of all `Film`s by
        // applying the Film.FILM_ID getter for each Film in the original stream
        final IntStream lengths = films.stream()
            .mapToInt(Film.FILM_ID.getter());

        // Print the ages
        lengths.forEachOrdered(System.out::println);

        // Creates a stream of Tuples with two elements: title and length
        Stream<Tuple2<String, Integer>> items = films.stream()
            .map(Tuples.toTuple(Film.TITLE.getter(), Film.LENGTH.getter()));

        // Print the id/name tuples
        items.forEachOrdered(System.out::println);

    }

    private void groupBy() {
        log("groupBy");
        Map<String, List<Film>> filmCategories = films.stream()
            .collect(
                Collectors.groupingBy(
                    Film.RATING.getter()
                )
            );

        filmCategories.forEach((k, v) -> {
            System.out.format("Rating %s has %d films%n", k, v.size());
        });
    }

    private void having() {
        log("having");
        Map<String, List<Film>> filmCategories = films.stream()
            .collect(
                Collectors.groupingBy(
                    Film.RATING.getter()
                )
            )
            .entrySet()
            .stream()
            .filter(e -> e.getValue().size() > 200)
            .collect(
                toMap(Entry::getKey, Entry::getValue)
            );

        System.out.println("Only categories with more than 200 films are shown");
        filmCategories.forEach((k, v) -> {
            System.out.format("Rating %s has %d films%n", k, v.size());
        });
    }

    private void distinct() {
        log("distinct");
        Set<String> ratings = films.stream()
            .map(Film.RATING.getter())
            .distinct()
            .collect(Collectors.toSet());

        System.out.println("The following ratings exists " + ratings);
    }

    private void orderBy() {
        log("orderBy");
        List<Film> filmsInLengthOrder = films.stream()
            .sorted(Film.LENGTH.comparator())
            .collect(Collectors.toList());

        filmsInLengthOrder.stream()
            ///.map(Tuples.toTuple(Film.LENGTH.getter(), Film.TITLE.getter()))
            .limit(10)
            .forEachOrdered(System.out::println);
    }

    private void offset() {
        log("offset");
        films.stream()
            .sorted(Film.TITLE.comparator())
            .skip(100)
            .forEachOrdered(System.out::println);
    }

    private void limit() {
        log("limit");
        films.stream()
            .sorted(Film.TITLE.comparator())
            .limit(3)
            .forEachOrdered(System.out::println);
    }

    private void offsetLimit() {
        log("offsetLimit");
        films.stream()
            .sorted(Film.TITLE.comparator())
            .skip(100)
            .limit(50)
            .forEachOrdered(System.out::println);
    }

    private void count() {
        log("count");
        long noLongFilms = films.stream()
            .filter(Film.LENGTH.greaterThan(120))
            .count();

        System.out.format("There are %d long films%n", noLongFilms);
    }

    private static final int PAGE_SIZE = 50;

    private List<Film> getPage(int page, Comparator<Film> comparator) {
        log("getPage(" + page + ", " + comparator + ")");
        return films.stream()
            .sorted(comparator)
            .skip(page * PAGE_SIZE)
            .limit(PAGE_SIZE)
            .collect(Collectors.toList());
    }

    private void partitionedBy() {
        log("partitionedBy");
        Map<Boolean, List<Film>> map = films.stream()
            .collect(
                Collectors.partitioningBy(Film.LENGTH.greaterThan(120))
            );

        map.forEach((k, v) -> {
            System.out.format("long is %5s has %d films%n", k, v.size());
        });

    }

    private void join() {
        log("join");
        Map<Language, List<Film>> languageFilmMap = films.stream()
            .collect(
                // Apply this foreign key classifier
                groupingBy(languages.finderBy(Film.LANGUAGE_ID))
            );

        languageFilmMap.forEach((k, v) -> {
            System.out.format("There are %d films in %s %n", v.size(), k.getName());
        });

    }

    private void unionAll() {
        log("unionAll");
        StreamComposition.concatAndAutoClose(
            films.stream().filter(Film.LENGTH.greaterThan(120)),
            films.stream().filter(Film.RATING.equal("PG-13"))
        )
            .forEachOrdered(System.out::println);

    }

    private void union() {
        log("union");
        StreamComposition.concatAndAutoClose(
            films.stream().filter(Film.LENGTH.greaterThan(120)),
            films.stream().filter(Film.RATING.equal("PG-13"))
        )
            .distinct()
            .forEachOrdered(System.out::println);

    }

    private void oneToMany() {
        log("oneToMany");
        languages.stream()
            .filter(Language.NAME.equal("English"))
            .flatMap(films.finderBackwardsBy(Film.LANGUAGE_ID))
            .forEach(System.out::println);
    }

    private void manyToOne() {
        log("manyToOne");
        films.stream()
            .filter(Film.RATING.equal("PG-13"))
            .map(languages.finderBy(Film.LANGUAGE_ID))
            .forEach(System.out::println);
    }

    private void manyToMany() {
        log("manyToMany");

        Map<Actor, List<Film>> filmographies = filmActors.stream()
            .collect(
                groupingBy(actors.finderBy(FilmActor.ACTOR_ID), // Applies the FilmActor to ACTOR classifier
                    Collectors.mapping(
                        films.finderBy(FilmActor.FILM_ID), // Applies the FilmActor to Film finder
                        Collectors.toList() // Use a List collector for downstream aggregation.
                    )
                )
            );

        // Print out the Map in a nice way
        filmographies.forEach((a, fl) -> {
            System.out.format(
                "%20s appered in films [%s)]%n",
                a.getFirstName() + " " + a.getLastName(),
                fl.stream()
                    .mapToInt(Film.FILM_ID.getter())
                    .mapToObj(Integer::toString)
                    .collect(joining(", "))
            );
        });

    }

    private void pivot() {
        log("pivot");

        MapStream.fromKeys(
            actors.stream(), // keys  : Stream<Actor>
            a -> filmActors.findBackwardsBy(FilmActor.ACTOR_ID, a) // values: Stream<ActorFilm>
                .map(films.finderBy(FilmActor.FILM_ID)) // values: Stream<Film>
                .collect(
                    Collectors.groupingBy(
                        Film.RATING.getter(), // Use 'rating' as classifier
                        Collectors.counting() // Count the occurrences rather than building a List
                    )
                ) // values: Map<String, Long>
        ) // MapStream<Actor, Map<String, Long>>

            .forEachOrdered((k, v) -> {                            // key: Actor, value: Map<String, Long>
                System.out.format("%22s  %5s %n", k.getFirstName() + " " + k.getLastName(), v);
            });

    }

    private void pivotTuple() {
        log("pivot");

        actors.stream()
            .map(Tuples.toTuple(
                Function.identity(),
                a -> filmActors.findBackwardsBy(FilmActor.ACTOR_ID, a) // values: Stream<ActorFilm>
                    .map(films.finderBy(FilmActor.FILM_ID)) // values: Stream<Film>
                    .collect(
                        Collectors.groupingBy(
                            Film.RATING.getter(), // Use 'rating' as classifier
                            Collectors.counting() // Count the occurrences rather than building a List
                        )
                    )
            ))
            .forEachOrdered(t2 -> {
                System.out.format("%22s  %5s %n", t2.get0().getFirstName() + " " + t2.get0().getLastName(), t2.get1());
            });

    }

    private void pivotClass() {
        log("pivot");

        class ActorMapRating {

            final Actor actor;
            final Map<String, Long> rateCount;

            public ActorMapRating(Actor actor, Map<String, Long> rateCount) {
                this.actor = actor;
                this.rateCount = rateCount;
            }

        };

        actors.stream()
            .map(actor -> new ActorMapRating(
            actor,
            filmActors.findBackwardsBy(FilmActor.ACTOR_ID, actor) // values: Stream<ActorFilm>
                .map(films.finderBy(FilmActor.FILM_ID)) // values: Stream<Film>
                .collect(
                    Collectors.groupingBy(
                        Film.RATING.getter(), // Use 'rating' as classifier
                        Collectors.counting() // Count the occurrences rather than building a List
                    )
                ))
            )
            .forEachOrdered(amr -> {
                System.out.format("%22s  %5s %n", amr.actor.getFirstName() + " " + amr.actor.getLastName(), amr.rateCount);
            });

    }

    private void log(String testName) {
        System.out.println();
        System.out.format("*** Starting test \"%s\" ***%n", testName);
    }

}
