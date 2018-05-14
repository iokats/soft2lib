/*******************************************************************************
 * Copyright 2018 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.zarkary.designpatterns.eventaggregator.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

/**
 * By the name one can easily say it aggregates events. An Publisher/ Subscriber
 * EventAggregator works as a HUB whose task is to aggregate all the published
 * messages and send the message to the interested subscribers.
 *
 * <p>The Event Aggregator pattern tries overcome the limitation of traditional
 * event handling approach by providing a central place to publish and subscribe
 * for events which is nothing but an Event Aggregator. The EventAggregator takes
 * care for registering, unregistering and invoking of events loosely coupling
 * {@link IPublisher}(s) and {@link ISubscriber}(s). All Publishers and Subscribers
 * of event will know only about the EventAggregator.</p>
 *
 * <p>The Event Aggregator pattern works like this:
 * <ol>
 *     <li>{@link IPublisher} publishes an {@link IMessage}</li>
 *     <li>{@link EventAggregator} receives {@link IMessage}(s) sent by
 *         {@link IPublisher}(s)</li>
 *     <li>{@link EventAggregator} gets a list of all {@link ISubscriber}(s)
 *         interested for a specific {@link IEventType} messages</li>
 *     <li>{@link EventAggregator} sends the {@link IMessage}(s) to the
 *         interested {@link ISubscriber}(s)</li>
 * </ol>
 * </p>
 *
 *
 * @author Ioannis Katsatos
 * @since 11.04.2018
 */
public class EventAggregator {

    private Map<IEventType, List<ISubscription>> subscriptionsByEventType;

    /**
     * Constructor
     *
     */
    public EventAggregator() {
        this.subscriptionsByEventType = new ConcurrentHashMap<>();
    }

    /**
     * Publishes the {@link IMessage}, which is passed from the {@link IPublisher},
     * to all interested {@link ISubscriber}(s). The selection of the subscribers
     * is based on the {@link IEventType} of the published message.
     *
     * <p>The passed {@link IMessage} must not be {@code null}, as well as and the
     * {@link IEventType} of the passed message.</p>
     *
     * @param message the published {@link IMessage} from the {@link IPublisher}
     * @param <T> the data type of {@link IMessage}
     */
    public <T> void publish(final IMessage<T> message) {
        requireNonNull(message);
        Optional.ofNullable(subscriptionsByEventType.get(requireNonNull(message.getEventType())))
                .ifPresent(subscriptions -> subscriptions.forEach(subscription -> subscription.onAction(message)));
    }

    /**
     * Subscribes an {@link IAction} for a specific {@link IEventType}.
     *
     * <p>It is allowed each {@link IEventType} entry, to have multiple
     * {@link IAction} registered.</p>
     *
     * @param eventType the type of event
     * @param action the {@link IAction} which will be invoked when the
     *        {@link IEventType} take place
     *
     * @param <T> the data type of {@link IMessage}, which passed to the {@link IAction}
     *
     * @return the {@link ISubscription} token which is used from {@link ISubscriber}
     */
    public <T> ISubscription subscribe(final IEventType eventType, final IAction<T> action) {
        final ISubscription subscription = new Subscription(eventType, action);
        subscriptionsByEventType.putIfAbsent(eventType, new ArrayList<>());
        subscriptionsByEventType.get(eventType).add(subscription);
        return subscription;
    }

    /**
     * Unsubscribe the given {@link ISubscription} from the {@link EventAggregator}.
     *
     * @param subscription token which will be unsubscribed from the {@link EventAggregator}
     */
    public void unsubscribe(final ISubscription subscription) {
        final IEventType eventType = requireNonNull(subscription).getEventType();
        if(subscriptionsByEventType.containsKey(eventType)) {
            subscriptionsByEventType.remove(eventType);
        }
    }
}
