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
public class ReferencePredicates {

    private final SakilaApplication app;
    private final FilmManager films;

    public ReferencePredicates() {
        app = buildApplication();
        films = app.getOrThrow(FilmManager.class);
    }

    public static void main(String[] args) {
        new ReferencePredicates().run();
    }

    private void run() {
        isNull();
        isNotNull();
    }

    private void isNull() {
        ExampleUtil.log("isNull");

        long count = films.stream()
            .filter(Film.RATING.isNull())
            .count();
        System.out.format("There are %d films with a null rating %n", count);
    }

    private void isNotNull() {
        ExampleUtil.log("isNotNull");

        long count = films.stream()
            .filter(Film.RATING.isNotNull())
            .count();
        System.out.format("There are %d films with a non-null rating %n", count);
    }

}
