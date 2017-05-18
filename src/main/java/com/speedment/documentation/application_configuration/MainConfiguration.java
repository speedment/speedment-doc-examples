package com.speedment.documentation.application_configuration;

import com.company.sakila.SakilaApplication;
import com.company.sakila.SakilaApplicationBuilder;
import com.company.sakila.db0.sakila.film.Film;
import com.company.sakila.db0.sakila.film.generated.GeneratedFilm;
import com.speedment.runtime.config.identifier.ColumnIdentifier;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.ApplicationBuilder.LogType;
import com.speedment.runtime.field.IntField;
import com.speedment.runtime.field.StringField;
import com.speedment.runtime.typemapper.TypeMapper;

/**
 *
 * @author Per Minborg
 */
public class MainConfiguration {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        simple();
        password();
        passwordTwoDbmses();
        logging();
    }

    private static void simple() {
        SakilaApplication app = new SakilaApplicationBuilder().withSkipCheckDatabaseConnectivity().build();
    }

    private static void password() {
        SakilaApplication app = new SakilaApplicationBuilder()
            .withPassword("Jz237@h1J19!")
            .withSkipCheckDatabaseConnectivity()
            .build();
    }

    private static void passwordTwoDbmses() {
        SakilaApplication app = new SakilaApplicationBuilder()
            // Set the password for the database that holds Film etc.
            .withPassword(Film.FILM_ID.identifier(), "Jz237@h1J19!")
            // Set the password for the database that holds Book etc.
            .withPassword(Book.BOOK_ID.identifier(), "AuW78hd&J19!")
            .withSkipCheckDatabaseConnectivity()
            .build();
    }

    private static void logging() {
        SakilaApplication app = new SakilaApplicationBuilder()
            .withPassword("Jz237@h1J19!")
            .withLogging(LogType.APPLICATION_BUILDER)
            .withLogging(LogType.STREAM)
            .withLogging(LogType.STREAM_OPTIMIZER)
            .withSkipCheckDatabaseConnectivity()
            .build();
    }

    private static class Book {

        public static IntField<Film, Integer> BOOK_ID = IntField.create(
            GeneratedFilm.Identifier.FILM_ID,
            Film::getFilmId,
            Film::setFilmId,
            TypeMapper.primitive(),
            true
        );

        public static StringField<Film, String> TITLE = StringField.create(
            GeneratedFilm.Identifier.TITLE,
            Film::getTitle,
            Film::setTitle,
            TypeMapper.identity(),
            false
        );

        enum Identifier implements ColumnIdentifier<Book> {

            FILM_ID("film_id"),
            TITLE("title");

            private final String columnName;
            private final TableIdentifier<Book> tableIdentifier;

            Identifier(String columnName) {
                this.columnName = columnName;
                this.tableIdentifier = TableIdentifier.of(getDbmsName(),
                    getSchemaName(),
                    getTableName());
            }

            @Override
            public String getDbmsName() {
                return "db1";
            }

            @Override
            public String getSchemaName() {
                return "library";
            }

            @Override
            public String getTableName() {
                return "book";
            }

            @Override
            public String getColumnName() {
                return this.columnName;
            }

            @Override
            public TableIdentifier<Book> asTableIdentifier() {
                return this.tableIdentifier;
            }
        }

    }
}
