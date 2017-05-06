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
import com.speedment.runtime.core.util.StreamComposition;
import java.util.function.Predicate;

/**
 *
 * @author Per Minborg
 */
public class CombiningPredicates {

    private final SakilaApplication app;
    private final FilmManager films;

    public CombiningPredicates() {
        app = buildApplication();
        films = app.getOrThrow(FilmManager.class);
    }

    public static void main(String[] args) {
        new CombiningPredicates().run();
    }

    private void run() {
        isOldAndStartsWithH_usingAnd();
        isOldAndStartsWithH_using2Filters();
        or();
        orUsing2Streams();
    }

    private void isOldAndStartsWithH_usingAnd() {
        ExampleUtil.log("isOldAndStartsWithH_usingAnd");

        Predicate<Film> isLong = Film.LENGTH.greaterThan(120);
        Predicate<Film> isPG13 = Film.RATING.equal("PG-13");

        Predicate<Film> isLongAndRatedPG13 = isLong.and(isPG13);

        films.stream()
            .filter(isLongAndRatedPG13)
            .forEachOrdered(System.out::println);
    }

    private void isOldAndStartsWithH_using2Filters() {
        ExampleUtil.log("isOldAndStartsWithH_using2Filters");

        films.stream()
            .filter(Film.LENGTH.greaterThan(120))
            .filter(Film.RATING.equal("PG-13"))
            .forEachOrdered(System.out::println);
    }

    private void or() {
        ExampleUtil.log("or");

        Predicate<Film> isLong = Film.LENGTH.greaterThan(120);
        Predicate<Film> isPG13 = Film.RATING.equal("PG-13");

        Predicate<Film> isLongOrRatedPG13 = isLong.or(isPG13);

        films.stream()
            .filter(isLongOrRatedPG13)
            .forEachOrdered(System.out::println);
    }

    private void orUsing2Streams() {
        ExampleUtil.log("orUsing2Streams");

        StreamComposition.concatAndAutoClose(
            films.stream().filter(Film.LENGTH.greaterThan(120)),
            films.stream().filter(Film.RATING.equal("PG-13"))
        )
            .distinct()
            .forEachOrdered(System.out::println);

    }

}
