package com.zarkary.designpatterns.creational.example.lazyness;

public class Heavy {

    public Heavy() {
        heavyMethod();
    }

    private void heavyMethod() {
        System.out.println("Heavy created");
    }

    @Override
    public String toString() {
        return "quite heavy";
    }
}
