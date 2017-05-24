package com.speedment.documentation.advanced;

import com.company.sakila.SakilaApplication;
import com.company.sakila.db0.sakila.film.Film;
import com.company.sakila.db0.sakila.film.FilmManager;
import static com.speedment.documentation.util.ExampleUtil.buildApplication;
import java.util.stream.Stream;

/**
 *
 * @author Per Minborg
 */
public class AutomaticClose {

    private final SakilaApplication app;
    private final FilmManager films;

    public AutomaticClose() {
        app = buildApplication();
        films = app.getOrThrow(FilmManager.class);
    }

    public static void main(String[] args) {
        new AutomaticClose().run();
    }

    private void run() {
        autoClose();
        tryResource();
        explicitClose();
    }

    private void autoClose() {
        films.stream()
            .onClose(() -> System.out.println("Close : autoClose"))
            .count();
    }

    private void tryResource() {
        try (Stream<Film> filmStream = films.stream()) {
            filmStream.onClose(() -> System.out.println("Close : tryResource"));
            filmStream.count();
        };
    }

    private void explicitClose() {
        Stream<Film> filmStream = films.stream();
        filmStream.onClose(() -> System.out.println("Close : explicitClose"));
        filmStream.count();
        filmStream.close();
    }

}
