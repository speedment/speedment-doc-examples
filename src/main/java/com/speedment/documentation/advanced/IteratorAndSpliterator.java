package com.speedment.documentation.advanced;

import com.company.sakila.SakilaApplication;
import com.company.sakila.db0.sakila.film.Film;
import com.company.sakila.db0.sakila.film.FilmManager;
import static com.speedment.documentation.util.ExampleUtil.buildApplication;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 *
 * @author Per Minborg
 */
public class IteratorAndSpliterator {

    private final SakilaApplication app;
    private final FilmManager films;

    public IteratorAndSpliterator() {
        app = buildApplication(
            b -> b.withAllowStreamIteratorAndSpliterator()
        );
        films = app.getOrThrow(FilmManager.class);
    }

    public static void main(String[] args) {
        new IteratorAndSpliterator().run();
    }

    private void run() {
//        iterator();
        iteratorWithClose();
    }
//
//    private void iterator() {
//        Iterator<Film> filmIterator = films.stream()
//            .iterator();
//
//    }

    private void iteratorWithClose() {
        try (Stream<Film> filmStream = films.stream()) {
            filmStream.onClose(() -> System.out.println("Close : iteratorWitchClose"));
            Iterator<Film> filmIterator = filmStream.iterator();
            filmIterator.forEachRemaining(System.out::println);
        };
    }

}
