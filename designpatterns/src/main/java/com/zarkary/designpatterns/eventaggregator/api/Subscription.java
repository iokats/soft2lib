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
