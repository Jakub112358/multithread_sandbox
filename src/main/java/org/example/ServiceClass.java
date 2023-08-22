package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiceClass {

    private static ScheduledExecutorService executorService;
    private static final AtomicInteger startedTasks = new AtomicInteger(0);
    private final String name;

    public ServiceClass(String name) {
        this.name = name;
    }

    private class myTask implements Runnable {
        @Override
        public void run() {
            System.out.println(name + ": final operation, by thread:  " + Thread.currentThread().getName());
            if (startedTasks.decrementAndGet() < 1) {
                System.out.println("shutting down executor service");
                executorService.shutdown();
            }
        }
    }

    public void doTask() {
        if (executorService == null || executorService.isShutdown()) {
            System.out.println("creating new executor service");
            executorService = Executors.newSingleThreadScheduledExecutor();
        }
        startedTasks.incrementAndGet();
        System.out.println(name + ": initial operation");
        executorService.schedule(new myTask(), 1L, TimeUnit.SECONDS);
    }
}
