package com.zarkary.designpatterns.creational.example.chocolateboiler;

import com.zarkary.designpatterns.creational.api.Create;
import com.zarkary.designpatterns.creational.api.Singleton;

public class App {

    private static void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        final Singleton<ChocolateBoiler> singleton = Create.lazily(() -> new ChocolateBoiler());
        final Runnable command = () -> {
            final ChocolateBoiler boiler = singleton.getInstance();
            System.out.println("Boiler instance: " + boiler);
            sleep(1000);

            boiler.fill();
            sleep(1000);

            boiler.boil();
            sleep(1000);

            boiler.drain();
            sleep(1000);
        };

        final Thread thread1 = new Thread(command);
        final Thread thread2 = new Thread(command);

        thread1.start();
        thread2.start();
    }
}
