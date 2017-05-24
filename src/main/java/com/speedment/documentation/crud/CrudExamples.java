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
package com.speedment.documentation.crud;

import com.company.sakila.SakilaApplication;
import com.company.sakila.db0.sakila.film.FilmManager;
import com.company.sakila.db0.sakila.language.Language;
import com.company.sakila.db0.sakila.language.LanguageImpl;
import com.company.sakila.db0.sakila.language.LanguageManager;
import com.speedment.documentation.util.ExampleUtil;
import static com.speedment.documentation.util.ExampleUtil.buildApplication;
import com.speedment.runtime.core.ApplicationBuilder.LogType;
import com.speedment.runtime.core.exception.SpeedmentException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author Per Minborg
 */
public class CrudExamples {

    private final SakilaApplication app;
    private final FilmManager films;
    private final LanguageManager languages;

    public CrudExamples() {
        app = buildApplication(b -> b
            .withLogging(LogType.PERSIST)
            .withLogging(LogType.REMOVE)
            .withLogging(LogType.UPDATE)
        );
        films = app.getOrThrow(FilmManager.class);
        languages = app.getOrThrow(LanguageManager.class);
    }

    public static void main(String[] args) {
        new CrudExamples().run();
    }

    private void run() {
        create();
        createInStream();
        update();
        updateInStream();
        delete();
        deleteInStream();
    }

    private void create() {
        ExampleUtil.log("create");

        final Language language = new LanguageImpl().setName("Deutsch");
        try {
            languages.persist(language);
        } catch (SpeedmentException se) {
            System.out.println("Failed to persist " + language + ". " + se.getMessage());
        }

    }

    private void createInStream() {
        ExampleUtil.log("createInStream");

        Stream.of("Italiano", "Español")
            .map(ln -> new LanguageImpl().setName(ln))
            .forEach(languages.persister());

    }

    private void update() {
        ExampleUtil.log("update");

        Optional<Language> italiano = languages.stream()
            .filter(Language.NAME.equal("Italiano"))
            .findFirst();

        italiano.ifPresent(l -> {
            l.setName("Italian");
            languages.update(l);
        });

        // This will do the same but in one step:
        languages.stream()
            .filter(Language.NAME.equal("Italiano"))
            .findFirst()
            .map(l -> l.setName("Italian"))
            .ifPresent(languages.updater());

    }

    private void updateInStream() {
        ExampleUtil.log("updateInStream");

        languages.stream()
            .filter(Language.NAME.equal("Deutsch"))
            .map(Language.NAME.setTo("German"))
            .forEach(languages.updater());
    }

    private void delete() {
        ExampleUtil.log("delete");

        Optional<Language> italiano = languages.stream()
            .filter(Language.NAME.equal("Italiano"))
            .findFirst();

        italiano.ifPresent(l -> languages.remove(l));

        // This will do the same but in one step:
        languages.stream()
            .filter(Language.NAME.equal("Italiano"))
            .findFirst()
            .ifPresent(languages.remover());

    }

    private void deleteInStream() {
        ExampleUtil.log("deleteInStream");

        languages.stream()
            .filter(Language.NAME.notEqual("English"))
            .forEach(languages.remover());
    }

}
