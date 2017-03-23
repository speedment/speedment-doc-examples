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
import com.speedment.runtime.core.util.StreamComposition;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Per Minborg
 */
public class CombiningPredicates {

    private final HaresApplication app;
    private final HareManager hares;

    public CombiningPredicates() {
        app = buildApplication();
        hares = app.getOrThrow(HareManager.class);
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

        Predicate<Hare> isAdult = Hare.AGE.greaterThan(2);
        Predicate<Hare> nameContains_e = Hare.NAME.contains("e");

        Predicate<Hare> isAdultAndNameContains_e = isAdult.and(nameContains_e);

        hares.stream()
            .filter(isAdultAndNameContains_e)
            .forEachOrdered(System.out::println);
    }

    private void isOldAndStartsWithH_using2Filters() {
        ExampleUtil.log("isOldAndStartsWithH_using2Filters");

        hares.stream()
            .filter(Hare.AGE.greaterThan(2))
            .filter(Hare.NAME.contains("e"))
            .forEachOrdered(System.out::println);
    }

    private void or() {
        ExampleUtil.log("or");

        Predicate<Hare> isAdult = Hare.AGE.greaterThan(2);
        Predicate<Hare> nameContains_e = Hare.NAME.contains("e");

        Predicate<Hare> isAdultOrNameContains_e = isAdult.or(nameContains_e);

        hares.stream()
            .filter(isAdultOrNameContains_e)
            .forEachOrdered(System.out::println);
    }

    private void orUsing2Streams() {
        ExampleUtil.log("orUsing2Streams");

        StreamComposition.concatAndAutoClose(
            hares.stream().filter(Hare.AGE.greaterThan(2)),
            hares.stream().filter(Hare.NAME.contains("e"))
        )
            .distinct()
            .forEachOrdered(System.out::println);

    }

}
