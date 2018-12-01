package com.zarkary.designpatterns.api;

import com.zarkary.designpatterns.eventaggregator.api.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventAggregatorTest {

    private final EventAggregator eventAggregator = new EventAggregator();

    @Before
    public void setUp() {

    }

    @Test(expected = NullPointerException.class)
    public void subscribe_whenPassedEventTypeIsNull_theNullPointerException() {

        // Arrange
        final IEventType nullEventType = null;
        final IAction action = mock(IAction.class);

        // Act
        eventAggregator.subscribe(nullEventType, action);

        // Assert
    }

    @Test(expected = NullPointerException.class)
    public void subscribe_whenPassedActionIsNull_theNullPointerException() {

        // Arrange
        final IEventType eventType = mock(IEventType.class);
        final IAction nullAction = null;

        // Act
        eventAggregator.subscribe(eventType, nullAction);

        // Assert
    }

    @Test
    public void subscribe_whenPassedEventTypeAndActionIsNotNull_theSubscriptionIsReturned() {

        // Arrange
        final IEventType eventType = mock(IEventType.class);
        final IAction action = mock(IAction.class);

        // Act
        final ISubscription subscription = eventAggregator.subscribe(eventType, action);

        // Assert
        assertEquals(eventType, subscription.getEventType());
    }

    @Test(expected = NullPointerException.class)
    public void publish_whenPassedMessageIsNull_thenNullPointerException() {

        // Arrange
        final IMessage nullMessage = null;

        // Act
        eventAggregator.publish(nullMessage);

        // Assert
    }

    @Test(expected = NullPointerException.class)
    public void publish_whenTheEventTypeOfPassedMessageIsNull_thenNothingIsHappening() {

        // Arrange
        final IEventType eventType = mock(IEventType.class);
        final IAction action = mock(IAction.class);
        final IMessage messageWithNullEventType = mock(IMessage.class);
        when(messageWithNullEventType.getEventType()).thenReturn(null);

        // Act
        eventAggregator.subscribe(eventType, action);
        eventAggregator.publish(messageWithNullEventType);

        // Assert
    }

    @Test
    public void publish_whenMessageWithoutRegisteredSubscribersForItsEventType_thenNothingIsHappening() {

        // Arrange
        final IEventType eventType = mock(IEventType.class);
        final IAction action = mock(IAction.class);
        final IMessage messageWithNotInterestedSubscribers = mock(IMessage.class);
        final IEventType notRegisteredEventType = mock(IEventType.class);
        when(messageWithNotInterestedSubscribers.getEventType()).thenReturn(notRegisteredEventType);

        // Act
        eventAggregator.subscribe(eventType, action);
        eventAggregator.publish(messageWithNotInterestedSubscribers);

        // Assert
        verify(action, times(0)).doAction(messageWithNotInterestedSubscribers);
    }

    @Test
    public void publish_whenMessageWithRegisteredSubscriberForItsEventType_thenInterestedSubscriberIsInvoked() {

        // Arrange
        final IEventType firstEventType = mock(IEventType.class);
        final IAction firstAction = mock(IAction.class);
        final IEventType secondEventType = mock(IEventType.class);
        final IAction secondAction = mock(IAction.class);

        final IMessage messageWithInterestedSubscriber = mock(IMessage.class);
        when(messageWithInterestedSubscriber.getEventType()).thenReturn(firstEventType);

        // Act
        eventAggregator.subscribe(firstEventType, firstAction);
        eventAggregator.subscribe(secondEventType, secondAction);
        eventAggregator.publish(messageWithInterestedSubscriber);

        // Assert
        verify(firstAction, times(1)).doAction(messageWithInterestedSubscriber);
        verify(secondAction, times(0)).doAction(messageWithInterestedSubscriber);
    }

    @Test
    public void publish_whenMessageWithRegisteredSubscribersForItsEventType_thenInterestedSubscribersAreInvoked() {

        // Arrange
        final IEventType firstEventType = mock(IEventType.class);
        final IEventType secondEventType = mock(IEventType.class);
        final IAction firstSubscribedAction = mock(IAction.class);
        final IAction secondSubscribedAction = mock(IAction.class);
        final IAction thirdSubscribedAction = mock(IAction.class);
        final IMessage messageWithInterestedSubscribers = mock(IMessage.class);
        when(messageWithInterestedSubscribers.getEventType()).thenReturn(firstEventType);


        // Act
        eventAggregator.subscribe(firstEventType, firstSubscribedAction);
        eventAggregator.subscribe(firstEventType, secondSubscribedAction);
        eventAggregator.subscribe(secondEventType, thirdSubscribedAction);
        eventAggregator.publish(messageWithInterestedSubscribers);

        // Assert
        verify(firstSubscribedAction, times(1)).doAction(messageWithInterestedSubscribers);
        verify(secondSubscribedAction, times(1)).doAction(messageWithInterestedSubscribers);
        verify(thirdSubscribedAction, times(0)).doAction(messageWithInterestedSubscribers);
    }

    @Test
    public void publish_whenSubscriberInterestedForMoreThanOneEventTypes_thenSubscriberInvokedForEachPublishedEvent() {

        // Arrange
        final IEventType firstEventType = mock(IEventType.class);
        final IEventType secondEventType = mock(IEventType.class);
        final IAction subscribedAction = mock(IAction.class);
        final IMessage messageWithFirstEventType = mock(IMessage.class);
        final IMessage messageWithSecondEventType = mock(IMessage.class);
        when(messageWithFirstEventType.getEventType()).thenReturn(firstEventType);
        when(messageWithSecondEventType.getEventType()).thenReturn(secondEventType);

        // Act
        eventAggregator.subscribe(firstEventType, subscribedAction);
        eventAggregator.subscribe(secondEventType, subscribedAction);
        eventAggregator.publish(messageWithFirstEventType);
        eventAggregator.publish(messageWithSecondEventType);

        // Assert
        verify(subscribedAction, times(1)).doAction(messageWithFirstEventType);
        verify(subscribedAction, times(1)).doAction(messageWithSecondEventType);
    }

    @Test(expected = NullPointerException.class)
    public void unsubscribe_whenPassedSubscriptionIsNull_thenNullPointerException() {

        // Arrange
        final ISubscription nullSubscription = null;

        // Act
        eventAggregator.unsubscribe(nullSubscription);

        // Assert
    }

    @Test
    public void unsubscribe_whenPassedSubscriptionIsValid_thenSubscriptionIsUnsubscribedNormally() {

        // Arrange
        final IEventType eventType = mock(IEventType.class);
        final IAction action = mock(IAction.class);
        final IMessage message = mock(IMessage.class);
        when(message.getEventType()).thenReturn(eventType);
        final ISubscription subscription = eventAggregator.subscribe(eventType, action);

        // Act
        eventAggregator.unsubscribe(subscription);
        eventAggregator.publish(message);

        // Assert
        verify(action, times(0)).doAction(message);
    }

    @Test
    public void unsubscribe_whenThereAreManySubscriptionsForTheSameEventType_thenGivenSubscriptionIsUnsubscribed() {

        // Arrange
        final IEventType eventType = mock(IEventType.class);

        final IAction firstAction = mock(IAction.class);
        final IMessage firstMessage = mock(IMessage.class);
        when(firstMessage.getEventType()).thenReturn(eventType);
        eventAggregator.subscribe(eventType, firstAction);

        final IAction secondAction = mock(IAction.class);
        final ISubscription secondSubscription = eventAggregator.subscribe(eventType, secondAction);

        // Act
        eventAggregator.unsubscribe(secondSubscription);
        eventAggregator.publish(firstMessage);

        // Assert
        verify(firstAction, times(1)).doAction(firstMessage);
    }
}
