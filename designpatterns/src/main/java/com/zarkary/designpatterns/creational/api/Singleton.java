package com.zarkary.designpatterns.creational.api;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * A wrapper class that represents a singleton object which is loaded lazily.
 *
 * @param <T> the actual singleton instance
 *
 * @author Ioannis Katsatos
 * @since 25.04.2018
 */
public class Singleton<T> {

    private Supplier<T> instanceSupplier;

    /**
     * Package protected constructor, in order to enforce the user to
     * make use of the {@link Create} util class.
     *
     * @param instanceSupplier the {@link Supplier} which holds the actual
     *                         instance, cannot be {@code null}
     */
    Singleton(Supplier<T> instanceSupplier) {
        this.instanceSupplier = new SynchronizedSupplier(Objects.requireNonNull(instanceSupplier));
    }

    /**
     * <p>Returns lazily a single instance of the passed object.</p>
     *
     * <p>The returned instance created lazily, the first time where this method
     * is called.</p>
     *
     * <p>During the first call the passed {@link Supplier} creates and returns
     * the instance which then is stored to the local variable {@code instance}.
     * After that we create a new supplier, which now returns the newly created
     * instance.</p>
     *
     * <p>This method is thread safe and also is very efficient, because the
     * {@code synchronized} block is called once, only the first time.</p>
     *
     * @return the single instance
     */
    public T getInstance() {
        final T instance = instanceSupplier.get();
        instanceSupplier = () -> instance;
        return instanceSupplier.get();
    }

    /**
     * Creates and returns the actual instance of the passed {@link Supplier},
     * via thread safe environment.
     */
    private class SynchronizedSupplier implements Supplier<T> {

        private Supplier<T> innerInstanceSupplier;
        private T instance;

        /**
         * Constructor
         *
         * @param instanceSupplier the {@link Supplier} which holds the actual
         *                         instance, cannot be {@code null}
         */
        SynchronizedSupplier(Supplier<T> instanceSupplier) {
            this.innerInstanceSupplier = instanceSupplier;
        }

        /**
         * This method creates the actual instance and then return it.
         * Uses "double-checked locking" in order to reduce the use of synchronization.
         *
         * @return the single instance
         */
        @Override
        public T get() {
            if(Objects.isNull(instance)) {
                synchronized (this) {
                    if(Objects.isNull(instance)) {

                        instance = innerInstanceSupplier.get();
                    }
                }
            }
            return instance;
        }
    }
}