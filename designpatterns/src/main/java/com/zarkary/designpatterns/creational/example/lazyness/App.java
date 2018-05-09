package com.zarkary.designpatterns.creational.example.lazyness;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class App {

    private static final int THREAD_POOL = 10;


    public static void main(String[] args) {

        System.out.println("*************************");

        final LazyHolder holder = new LazyHolder();

        System.out.println("deferring heavy creation...");

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(THREAD_POOL);

        for(int i = 0; i < THREAD_POOL; i++) {
            scheduler.submit(() -> System.out.println(holder.getHeavy()));
        }

        scheduler.shutdown();

        System.out.println("*************************");
    }
}
