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

/**
 *
 * @author Per Minborg
 */
public class StringPredicates {

    private final HaresApplication app;
    private final HareManager hares;

    public StringPredicates() {
        app = buildApplication();
        hares = app.getOrThrow(HareManager.class);
    }

    public static void main(String[] args) {
        new StringPredicates().run();
    }

    private void run() {
        isEmpty();
        isNotNull();
    }

    private void isEmpty() {
        ExampleUtil.log("isEmpty");

        hares.stream()
            .filter(Hare.NAME.isEmpty())
            .forEachOrdered(System.out::println);
    }

    private void isNotNull() {
        ExampleUtil.log("isNotNull");
        
        hares.stream()
            .filter(Hare.NAME.isNotEmpty())
            .forEachOrdered(System.out::println);
    }

}