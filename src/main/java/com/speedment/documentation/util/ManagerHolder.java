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
package com.speedment.documentation.util;

import com.company.sakila.SakilaApplication;
import com.company.sakila.db0.sakila.actor.ActorManager;
import com.company.sakila.db0.sakila.film.FilmManager;
import com.company.sakila.db0.sakila.film_actor.FilmActorManager;
import com.company.sakila.db0.sakila.language.LanguageManager;

/**
 *
 * @author Per Minborg
 */
public class ManagerHolder {

    protected final FilmManager films;
    protected final ActorManager actors;
    protected final FilmActorManager filmActors;
    protected final LanguageManager languages;

    public ManagerHolder(final SakilaApplication app) {
        this.films = app.getOrThrow(FilmManager.class);
        this.actors = app.getOrThrow(ActorManager.class);
        this.filmActors = app.getOrThrow(FilmActorManager.class);
        this.languages = app.getOrThrow(LanguageManager.class);
    }

}
