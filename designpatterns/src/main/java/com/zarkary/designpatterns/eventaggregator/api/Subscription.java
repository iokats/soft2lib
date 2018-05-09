package com.zarkary.designpatterns.eventaggregator.api;

import static java.util.Objects.requireNonNull;

/**
 * This class holds together the {@link IEventType}, and the {@link IAction}
 * entities.
 *
 * <P>When the {@link IPublisher} publishes an event, which has the same type
 * with this subscription, then the {@link EventAggregator} calls the onAction
 * method in order to execute the passed IAction.</p>
 *
 * <p>As we can see, from the following code, the {@link IMessage} is passed
 * as parameter to the IAction.</p>.
 *
 * @param <T> the data type of message content
 *
 * @author Ioannis Katsatos
 * @since 02.04.2018
 */
public class Subscription<T> implements ISubscription<T> {

    private final IEventType eventType;
    private final IAction<T> action;

    /**
     * Constructor
     *
     * @param eventType the {@link IEventType} of this subscription
     * @param action    the {@link IAction} which is executed when an event
     *                  of {@link IEventType} occur
     */
    public Subscription(final IEventType eventType, final IAction<T> action) {
        this.eventType = requireNonNull(eventType);
        this.action = requireNonNull(action);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEventType getEventType() {
        return eventType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAction(final IMessage<T> message) {
        action.doAction(message);
    }
}
