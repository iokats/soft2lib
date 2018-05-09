package com.zarkary.designpatterns.creational;

import com.zarkary.designpatterns.creational.api.Create;
import com.zarkary.designpatterns.creational.api.Singleton;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateTest {

    private final int NUM_OF_THREADS = 3;

    private final CountDownLatch startAllThreads = new CountDownLatch(NUM_OF_THREADS);
    private final CountDownLatch finishAllThreads = new CountDownLatch(NUM_OF_THREADS);

    private Boolean isInitialized;
    Singleton<TestObject> singleton;

    @Before
    public void setUp() {
        isInitialized = false;
    }

    @Test(expected = NullPointerException.class)
    public void lazily_whenThePassedSupplierIsNull_then() {

        // Arrange
        final Supplier nullInstanceSupplier = null;

        // Act
        Create.lazily(nullInstanceSupplier);

        // Assert
    }

    @Test
    public void lazily_whenThePassedSupplierReturnsNull_thenNullableSingleton() {

        // Arrange
        final Supplier supplierOfNullInstance = () -> null;

        // Act
        final Singleton nullSingleton = Create.lazily(supplierOfNullInstance);

        // Assert
        assertNull(nullSingleton.getInstance());
    }

    @Test
    public void lazily_whenTheGetInstanceMethodIsNotCalled_thenAnInstanceIsNotInitialized() {

        // Arrange
        final Supplier<TestObject> instanceSupplier = () -> new TestObject();

        // Act
        Create.lazily(instanceSupplier);

        // Assert
        assertFalse(isInitialized);
    }

    @Test
    public void getInstance_whenTheGetInstanceMethodIsCalled_thenAnInstanceIsInitialized() {

        // Arrange
        final Supplier<TestObject> instanceSupplier = () -> new TestObject();
        final Singleton<TestObject> singleton = Create.lazily(instanceSupplier);

        // Act
        singleton.getInstance();

        // Assert
        assertTrue(isInitialized);
    }

    @Test
    public void getInstance_whenTheGetInstanceMethodIsCalledMoreTimes_thenWeAlwaysGetTheSameInstance() {

        // Arrange
        final Supplier<TestObject> instanceSupplier = () -> new TestObject();
        final Singleton<TestObject> singleton = Create.lazily(instanceSupplier);

        // Act
        final TestObject instanceFromTheFirstCall = singleton.getInstance();
        final TestObject instanceFromTheSecondCall = singleton.getInstance();

        // Assert
        assertEquals(instanceFromTheFirstCall, instanceFromTheSecondCall);
    }

    @Test
    public void getInstance_whenMultiThreadCalls_thenOnlyOneInstanceInitialized() throws InterruptedException {

        // Arrange
        singleton = Create.lazily(() -> new TestObject());
        final List<TestObject> lazyInstances = Collections.synchronizedList(new ArrayList<>());

        final Runnable reduceLatchAndWaitForAllThreadsToStart = () -> {
            try {
                startAllThreads.countDown();
                startAllThreads.await();
                lazyInstances.add(singleton.getInstance());
                finishAllThreads.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        final Thread thread1 = new Thread(reduceLatchAndWaitForAllThreadsToStart);
        final Thread thread2 = new Thread(reduceLatchAndWaitForAllThreadsToStart);
        final Thread thread3 = new Thread(reduceLatchAndWaitForAllThreadsToStart);

        // Act
        thread1.start();
        thread2.start();
        thread3.start();

        finishAllThreads.await();

        // Assert
        assertEquals(3, lazyInstances.size());
        assertEquals(lazyInstances.get(0), lazyInstances.get(1));
        assertEquals(lazyInstances.get(1), lazyInstances.get(2));
    }

    private class TestObject {
        TestObject() {
            isInitialized = true;
        }
    }
}
