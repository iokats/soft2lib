package com.zarkary.designpatterns.eventaggregator.api;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

/**
 * An implementation of the {@link ISubscriber} interface. This class holds a
 * reference to the {@link EventAggregator}, in order to subscribe/ unsubscribe
 * {@link IAction} per {@link IEventType}.
 *
 * <p>Also, the current implementation of the Subscriber, maybe interested in for
 * more than one distinct IEventTypes, with different IAction for each one.</p>
 *
 * @author Ioannis Katsatos
 * @since 03.04.2018
 */
public class Subscriber implements ISubscriber {

    /**
     * Mapping between {@link IEventType} and {@link ISubscription} token. This
     * information is used from the Subscriber to handle the subscriptions (e.g
     * unsubscribe some subscription from the EventAggregator).
     */
    private final Map<IEventType, ISubscription> subscriptionTokens;

    private final EventAggregator eventAggregator;

    /**
     * Constructor
     *
     * @param eventAggregator the {@link EventAggregator}
     */
    public Subscriber(final EventAggregator eventAggregator) {
        this.eventAggregator = requireNonNull(eventAggregator);
        this.subscriptionTokens  = new ConcurrentHashMap<>();
    }

    /**
     * Subscribes an {@link IAction} to the {@link EventAggregator} for a specific
     * {@link IEventType}. The EventAggregator returns back to the Subscriber (in order
     * to keep track on the Subscriptions) an {@link ISubscription} token.
     *
     * <p>If there is already registered an {@link IAction} for a specific {@link IEventType}
     * and we try to subscribe a new one {@link IAction} for the same {@link IEventType}, then
     * we get an {@link IllegalArgumentException}, because we need first to unsubscribe the old
     * one {@link IAction} and after that to add the new one.</p>
     *
     * @param eventType the {@link IEventType} which the Subscriber is interested in
     * @param action the {@link IAction} which will be executed when an event of
     *                the given IEventType take place
     * @param <T> the type of {@link IAction} content
     */
    @Override
    public <T> void subscribe(final IEventType eventType, final IAction<T> action) {

        if(subscriptionTokens.containsKey(requireNonNull(eventType))) {
            throw new IllegalArgumentException(
                    "There is already registered an IAction for this IEventType.");
        }

        final ISubscription subscription = eventAggregator.subscribe(eventType, requireNonNull(action));
        subscriptionTokens.put(eventType, subscription);
    }

    /**
     * Unsubscribe an {@link IEventType} from the {@link EventAggregator}
     *
     * @param eventType the {@link IEventType} which the Subscriber not more
     *                 is interested in
     */
    @Override
    public void unsubscribe(final IEventType eventType) {
        Optional.ofNullable(subscriptionTokens.remove(requireNonNull(eventType)))
                .ifPresent(eventAggregator::unsubscribe);
    }

    /**
     * @return an immutable collection with the {@link ISubscription}(s) of the
     *         Subscriber
     */
    public Collection<ISubscription> getSubscriptionTokens() {
        return Collections.unmodifiableCollection(subscriptionTokens.values());
    }

    /**
     * Place holder for releasing all the resources which are used from this entity.
     */
    @Override
    public void dispose() {
        getSubscriptionTokens().forEach(eventAggregator::unsubscribe);
        subscriptionTokens.clear();
    }
}
