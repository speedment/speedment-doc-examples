package com.speedment.documentation.advanced;

import com.company.sakila.SakilaApplication;
import com.company.sakila.db0.sakila.film.FilmManager;
import static com.speedment.documentation.util.ExampleUtil.buildApplication;
import com.speedment.runtime.core.ApplicationBuilder.LogType;
import com.speedment.runtime.core.component.connectionpool.ConnectionPoolComponent;
import java.util.stream.IntStream;

/**
 *
 * @author Per Minborg
 */
public class ConnectionPool {

    private final SakilaApplication app;
    private final FilmManager films;
    private final ConnectionPoolComponent connectionPool;

    public ConnectionPool() {
        app = buildApplication(
            b -> b
                .withParam("connectionpool.maxAge", "8000")
                .withParam("connectionpool.maxRetainSize", "10")
                .withLogging(LogType.CONNECTION)
                .withLogging(LogType.APPLICATION_BUILDER)
        );
        films = app.getOrThrow(FilmManager.class);
        connectionPool = app.getOrThrow(ConnectionPoolComponent.class);
    }

    public static void main(String[] args) {
        new ConnectionPool().run();
    }

    private void run() {
        singleThread();
        parallelThreads();
        afterSpike();
    }

    private void singleThread() {
        System.out.println("singleThread");
        IntStream.range(0, 10).forEach(this::printCount);
    }

    private void parallelThreads() {
        System.out.println("parallelThreads");
        IntStream.range(0, 10).parallel().forEach(this::printCount);
    }

    private void afterSpike() {
        System.out.println("afterSpike");

        printComnectionPoolInfo();

        IntStream.range(0, 15).forEach(i -> {
            sleep(1000);
            printComnectionPoolInfo();
            printCount(i);
//            System.out.format("There are %d connections in the pool.%n", connectionPool.poolSize());
        });
    }

    private void printCount(int callNo) {
        System.out.format("%2d: by thread %2d: There are %d films. There are %d connections in the pool.%n",
            callNo,
            Thread.currentThread().getId(),
            films.stream().count(),
            connectionPool.poolSize()
        );
    }

    private void printComnectionPoolInfo() {
        System.out.format("poolSize:%d, leaseSize:%d, maxAge:%d, maxRetainSize:%d%n",
            connectionPool.poolSize(),
            connectionPool.leaseSize(),
            connectionPool.getMaxAge(),
            connectionPool.getMaxRetainSize()
        );

    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

}
