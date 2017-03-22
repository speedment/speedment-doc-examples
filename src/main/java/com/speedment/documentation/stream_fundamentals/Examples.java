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
package com.speedment.documentation.stream_fundamentals;

import com.speedment.documentation.util.ExampleUtil;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Examples {

    public static void main(String[] args) {
        printFirstTen();
        countNumberOfFordSupporters();
        averageAgeOfTeslaLikers();
        youngestVolvoDigger();
        collectAllFiatLoversInAList();
    }

    private static void printFirstTen() {

        UserManager.stream()
            .limit(10)
            .forEachOrdered(
                u -> System.out.println(u.toString())
            );

    }

    private static void countNumberOfFordSupporters() {
        ExampleUtil.log("countNumberOfFordSupporters");

        long count = UserManager.stream()
            .filter(u -> "Ford".equals(u.getFavoriteCar()))
            .count();

        System.out.format("There are %d users that supports Ford %n", count);

    }

    private static void averageAgeOfTeslaLikers() {
        ExampleUtil.log("averageAgeOfTeslaLikers");

        OptionalDouble avg = UserManager.stream()
            .filter(u -> "Tesla".equals(u.getFavoriteCar()))
            .mapToInt(u -> 2017 - u.getBornYear()) // Calculates the age
            .average();

        if (avg.isPresent()) {
            System.out.format("The average age of Tesla likers are %f years %n", avg.getAsDouble());
        } else {
            System.out.format("There are no Tesla lovers");
        }

    }

    private static void youngestVolvoDigger() {
        ExampleUtil.log("youngestVolvoDigger");

        Comparator<User> comparator = Comparator.comparing(User::getBornYear).reversed();

        Optional<User> youngest = UserManager.stream()
            .filter(u -> "Volvo".equals(u.getFavoriteCar()))
            .sorted(comparator)
            .findFirst();

        youngest.ifPresent(u
            -> System.out.println("Found the youngest Volvo digger which is :" + u.toString())
        );

    }

    private static void collectAllFiatLoversInAList() {
        ExampleUtil.log("collectAllFiatLoversInAList");

        List<User> fiatLovers = UserManager.stream()
            .filter(u -> "Fiat".equals(u.getFavoriteCar()))
            .collect(Collectors.toList());

        System.out.format("There are %d fiat lovers %n", fiatLovers.size());

    }

    static class User {

        private static final List<String> CARS = Arrays.asList("Toyota", "Volvo", "Tesla", "Fiat", "Ford");

        private final int id;

        public User(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return "Name" + id;
        }

        public String getPassword() {
            return "PW" + (Integer.reverse(id ^ 0x7FE7E294) >>> 1);
        }

        public String getFavoriteCar() {
            return CARS.get(id % CARS.size());
        }

        public int getBornYear() {
            return 1950 + id % 50;
        }

        @Override
        public String toString() {
            return String.format(
                "{id=%d, name=%s, password=%s, favoriteCar=%s, bornYear=%d}",
                getId(),
                getName(),
                getPassword(),
                getFavoriteCar(),
                getBornYear()
            );
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof User)) {
                return false;
            }
            User that = (User) obj;
            return this.id == that.id;
        }

        @Override
        public int hashCode() {
            return id;
        }

    }

    static class UserManager {

        static Stream<User> stream() {
            return IntStream.range(0, 1000)
                .mapToObj(User::new);
        }
    }

}
