package com.zarkary.designpatterns.api;

import com.zarkary.designpatterns.eventaggregator.api.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionTest {

    @Mock
    private IEventType eventType;

    @Mock
    private IAction action;

    private ISubscription subscription;

    @Before
    public void setUp() {

        subscription = new Subscription(eventType, action);
    }

    @Test(expected = NullPointerException.class)
    public void subscription_whenEventTypeIsNull_thenNullPointerException() {

        // Arrange
        final IEventType nullEventType = null;

        // Act
        new Subscription<>(nullEventType, action);

        // Assert
    }

    @Test(expected = NullPointerException.class)
    public void subscription_whenActionIsNull_thenNullPointerException() {

        // Arrange
        final IAction nullAction = null;

        // Act
        new Subscription<>(eventType, nullAction);

        // Assert
    }

    @Test
    public void onAction_whenPassedMessageIsNull_thenActionIsExecutedNormally() {

        // Arrange
        final IMessage nullMessage = mock(IMessage.class);

        // Act
        subscription.onAction(nullMessage);

        // Assert
        verify(action, times(1)).doAction(nullMessage);
    }

    @Test
    public void onAction_whenPassedMessageIsNotNull_thenActionIsExecutedNormally() {

        // Arrange
        final IMessage message = mock(IMessage.class);

        // Act
        subscription.onAction(message);

        // Assert
        verify(action, times(1)).doAction(message);
    }

    @Test
    public void getEventType_whenIsCalled_thenReturnsTheEventType() {

        // Arrange

        // Act
        final IEventType returnedEventType = subscription.getEventType();

        // Assert
        assertEquals(eventType, returnedEventType);
    }
}
