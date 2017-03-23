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

import com.speedment.datamodel.HaresApplication;
import com.speedment.datamodel.db0.hares.hare.Hare;
import com.speedment.datamodel.db0.hares.hare.HareManager;
import com.speedment.documentation.util.ExampleUtil;
import static com.speedment.documentation.util.ExampleUtil.buildApplication;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 *
 * @author Per Minborg
 */
public class Examples {

    private final HaresApplication app;
    private final HareManager hares;

    public Examples() {
        app = buildApplication();
        hares = app.getOrThrow(HareManager.class);
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

        hares.stream()
            .limit(10)
            .forEachOrdered(System.out::println);
    }

    private void unOptimized() {
        ExampleUtil.log("unOptimized");

        hares.stream()
            .sorted(Comparator.comparing(Hare::getName))
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

        Comparator<Hare> nameOrder = Hare.NAME.comparator();

        hares.stream()
            .sorted(nameOrder)
            .forEachOrdered(System.out::println);

    }

    private void primitiveComparatorType() {
        ExampleUtil.log("primitiveComparatorType");

        Comparator<Hare> idComparator = Hare.ID.comparator();

        System.out.println(idComparator.getClass().getName());

    }

}
