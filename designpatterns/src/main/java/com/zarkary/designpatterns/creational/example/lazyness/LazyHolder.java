package com.zarkary.designpatterns.creational.example.lazyness;

import com.zarkary.designpatterns.creational.api.Create;
import com.zarkary.designpatterns.creational.api.Singleton;

public class LazyHolder {

    private Singleton<Heavy> heavy = Create.lazily(() -> new Heavy());

    public LazyHolder() {
        System.out.println("Holder created");
    }

    public Heavy getHeavy() {
        return heavy.getInstance();
    }
}
