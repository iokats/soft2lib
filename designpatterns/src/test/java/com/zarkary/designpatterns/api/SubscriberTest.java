package com.zarkary.designpatterns.api;

import com.zarkary.designpatterns.eventaggregator.api.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SubscriberTest {

    @Mock
    private EventAggregator eventAggregator;

    private Subscriber subscriber;

    @Before
    public void setUp() {

        subscriber = new Subscriber(eventAggregator);
    }

    @Test(expected = NullPointerException.class)
    public void subscriber_whenPassedEventAggregatorIsNull_thenNullPointerException() {

        // Arrange
        final EventAggregator nullEventAggregator = null;

        // Act
        new Subscriber(nullEventAggregator);

        // Assert
    }

    @Test(expected = NullPointerException.class)
    public void subscribe_whenPassedEventTypeIsNull_thenNullPointerException() {

        // Arrange
        final IEventType nullEventType = null;
        final IAction action = mock(IAction.class);

        // Act
        subscriber.subscribe(nullEventType, action);

        // Assert
    }

    @Test(expected = NullPointerException.class)
    public void subscribe_whenPassedActionIsNull_thenNullPointerException() {

        // Arrange
        final IEventType eventType = mock(IEventType.class);
        final IAction nullAction = null;

        // Act
        subscriber.subscribe(eventType, nullAction);

        // Assert
    }

    @Test(expected = IllegalArgumentException.class)
    public void subscribe_whenPassedEventTypeIsAlreadySubscribed_theIllegalArgumentException() {

        // Arrange
        final IEventType eventType = mock(IEventType.class);
        final IAction firstAction = mock(IAction.class);
        final IAction secondAction = mock(IAction.class);
        final ISubscription firstSubscription = mock(ISubscription.class);
        final ISubscription secondSubscription = mock(ISubscription.class);
        when(eventAggregator.subscribe(eventType, firstAction)).thenReturn(firstSubscription);
        when(eventAggregator.subscribe(eventType, secondAction)).thenReturn(secondSubscription);

        // Act
        subscriber.subscribe(eventType, firstAction);
        subscriber.subscribe(eventType, secondAction);

        // Assert
    }

    @Test
    public void subscribe_whenPassedEventTypeAndActionIsNotNull_thenSubscriptionIsCreatedToEventAggregator() {

        // Arrange
        final IEventType eventType = mock(IEventType.class);
        final IAction action = mock(IAction.class);
        final ISubscription subscription = mock(ISubscription.class);
        when(eventAggregator.subscribe(eventType, action)).thenReturn(subscription);

        // Act
        subscriber.subscribe(eventType, action);

        // Assert
        verify(eventAggregator, times(1)).subscribe(eventType, action);
    }

    @Test
    public void subscribe_whenPassedEventTypeAndActionIsNotNull_thenSubscriptionWithThePassedEventType() {

        // Arrange
        final IEventType eventType = mock(IEventType.class);
        final IAction action = mock(IAction.class);
        final ISubscription subscriptionMock = mock(ISubscription.class);
        when(subscriptionMock.getEventType()).thenReturn(eventType);
        when(eventAggregator.subscribe(eventType, action)).thenReturn(subscriptionMock);

        // Act
        subscriber.subscribe(eventType, action);

        // Assert
        final ISubscription subscription = subscriber.getSubscriptionTokens().iterator().next();
        assertEquals(eventType, subscription.getEventType());
    }

    @Test(expected = NullPointerException.class)
    public void unsubscribe_whenPassedEventTypeIsNull_thenEventAggregatorNotUnsubscribeIt() {

        // Arrange
        final IEventType nullEventType = null;

        // Act
        subscriber.unsubscribe(nullEventType);

        // Assert
    }

    @Test
    public void unsubscribe_whenPassedEventTypeIsNotNull_thenItsSubscriptionIsRemoved() {

        // Arrange
        final IEventType registeredEventType = mock(IEventType.class);
        final IAction registeredAction = mock(IAction.class);
        final ISubscription subscription = mock(ISubscription.class);
        when(eventAggregator.subscribe(registeredEventType, registeredAction)).thenReturn(subscription);

        // Act
        subscriber.subscribe(registeredEventType, registeredAction);
        subscriber.unsubscribe(registeredEventType);

        // Assert
        final Collection<ISubscription> subscriptions = subscriber.getSubscriptionTokens();
        assertEquals(0, subscriptions.size());
    }


    @Test
    public void unsubscribe_whenPassedEventTypeIsAlreadyRegistered_thenEventAggregatorUnsubscribeIt() {

        // Arrange
        final IEventType registeredEventType = mock(IEventType.class);
        final IAction registeredAction = mock(IAction.class);
        final ISubscription subscriptionMock = mock(ISubscription.class);
        when(subscriptionMock.getEventType()).thenReturn(registeredEventType);
        when(eventAggregator.subscribe(registeredEventType, registeredAction)).thenReturn(subscriptionMock);

        // Act
        subscriber.subscribe(registeredEventType, registeredAction);
        subscriber.unsubscribe(registeredEventType);

        // Assert
        verify(eventAggregator, times(1)).unsubscribe(any());
    }

    @Test
    public void dispose_whenWeDoNotHaveRegisteredTokens_thenEventAggregatorUnsubscribeIsNotCalled() {

        // Arrange

        // Act
        subscriber.dispose();

        // Assert
        verify(eventAggregator, times(0)).unsubscribe(any());
    }

    @Test
    public void dispose_whenWeHaveRegisteredTokens_thenEventAggregatorUnsubscribeIsCalled() {

        // Arrange
        final IEventType eventType = mock(IEventType.class);
        final IAction action = mock(IAction.class);
        final ISubscription subscription = mock(ISubscription.class);
        when(eventAggregator.subscribe(eventType, action)).thenReturn(subscription);

        // Act
        subscriber.subscribe(eventType, action);
        subscriber.dispose();

        // Assert
        verify(eventAggregator, times(1)).unsubscribe(subscription);
    }

    @Test
    public void dispose_whenWeHaveRegisteredTokens_thenGetSubscriptionTokensIsEmpty() {

        // Arrange
        final IEventType eventType = mock(IEventType.class);
        final IAction action = mock(IAction.class);
        final ISubscription subscription = mock(ISubscription.class);
        when(eventAggregator.subscribe(eventType, action)).thenReturn(subscription);

        // Act
        subscriber.subscribe(eventType, action);
        subscriber.dispose();

        // Assert
        final Collection<ISubscription> subscriptions = subscriber.getSubscriptionTokens();
        assertEquals(0, subscriptions.size());
    }
}
