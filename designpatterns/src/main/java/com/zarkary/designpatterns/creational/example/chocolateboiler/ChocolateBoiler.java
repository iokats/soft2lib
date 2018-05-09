package com.zarkary.designpatterns.creational.example.chocolateboiler;

public class ChocolateBoiler {

    private boolean empty;
    private boolean boiled;

    public ChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    public void fill() {
        if(isEmpty()) {
            System.out.println("Thread-" + Thread.currentThread() + "Boiler is Filled");
            empty = false;
            boiled = false;
        }
    }

    public void drain() {
        if(!isEmpty() && isBoiled()) {
            System.out.println("Thread-" + Thread.currentThread() + "Boiler is Drained");
            empty = true;
        }
    }

    public void boil() {
        if(!isEmpty() && !isBoiled()) {
            System.out.println("Thread-" + Thread.currentThread() + "Boiler is Boiled");
            boiled = true;
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }
}
