package com.speedment.documentation.advanced;

import com.company.sakila.SakilaApplication;
import com.company.sakila.db0.sakila.inventory.Inventory;
import com.company.sakila.db0.sakila.inventory.InventoryManager;
import static com.speedment.documentation.util.ExampleUtil.buildApplication;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.core.stream.parallel.ParallelStrategy;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Per Minborg
 */
public class Parallel {

    private final SakilaApplication app;
    private final InventoryManager inventories;

    public Parallel() {
        app = buildApplication();
        inventories = app.getOrThrow(InventoryManager.class);
    }

    public static void main(String[] args) {
        new Parallel().run();
    }

    private void run() {
        parallel();
        strategy();
        inOtherThreadPool();
    }

    private void parallel() {
        System.out.println("parallel");
        
        inventories.stream()
            .parallel()
            .forEach(this::expensiveOperation);
    }

    private void expensiveOperation(Inventory inventory) {
        System.out.println("expensiveOperation");
        
        try {
            Thread.sleep(100);
            System.out.format("%32s %s%n", Thread.currentThread().getName(), inventory);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

    private void strategy() {
        System.out.println("strategy");

        Manager<Inventory> inventoriesWithStategy = app
            .configure(InventoryManager.class)
            .withParallelStrategy(ParallelStrategy.computeIntensityHigh())
            .build();

        inventoriesWithStategy.stream()
            .parallel()
            .forEach(this::expensiveOperation);
    }

    private void inOtherThreadPool() {
        System.out.println("inOtherThreadPool");

        // Create a custom ForkJoinPool with only three threads
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);

        forkJoinPool.submit(() -> {
            inventories.stream()
                .parallel()
                .forEach(this::expensiveOperation);
        });

        try {
            forkJoinPool.shutdown();
            forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

}
