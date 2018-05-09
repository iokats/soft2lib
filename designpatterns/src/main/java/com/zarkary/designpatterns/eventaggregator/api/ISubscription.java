package com.zarkary.designpatterns.eventaggregator.api;

/**
 * It is a class to create {@link ISubscription} tokens. When a {@link ISubscriber}
 * subscribes to interested {@link IEventType} via {@link EventAggregator} the
 * EventAggregator returns a subscription token that is further used by the subscriber
 * to keep track of its subscriptions.
 *
 * @param <T> the data type of message content
 *
 * @author Ioannis Katsatos
 * @since 02.04.2018
 *
 */
public interface ISubscription<T> {

    /**
     * @return the {@link IEventType} of this subscription
     */
    IEventType getEventType();

    /**
     * This method is called from the {@link EventAggregator}, when an event with
     * {@link IEventType} same with that of this subscription take place.
     *
     * @param message the message which is published as result of the occurred event
     */
    void onAction(IMessage<T> message);
}
