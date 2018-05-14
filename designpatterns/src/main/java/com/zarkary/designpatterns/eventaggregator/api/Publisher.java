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
 * Technically Publisher is a generic class that inherits the {@link IPublisher}
 * interface. Since Publisher is a generic class, it can publishes different type
 * of {@link IMessage}/content for different type of {@link IEventType}(s).
 *
 *
 * @author Ioannis Katsatos
 * @since 30.03.2018
 */
public class Publisher implements IPublisher {

    private final EventAggregator eventAggregator;

    /**
     * Constructor
     *
     * @param eventAggregator the {@link EventAggregator} to which the
     *              {@link IPublisher} will publish its {@link IMessage}(s)
     */
    public Publisher(final EventAggregator eventAggregator) {
        this.eventAggregator = requireNonNull(eventAggregator);
    }

    /**
     * The Publisher publishes a {@link IMessage} to the {@link EventAggregator}
     * by calling its publish method.
     *
     * @param message the message which is going to be published
     * @param <T> the type of message
     */
    @Override
    public <T> void publish(final IMessage<T> message) {
        eventAggregator.publish(message);
    }

    /**
     * The Publisher publishes, to the {@link EventAggregator}, the content which
     * corresponds to a specific {@link IEventType} type.
     *
     * @param eventType the type of event
     * @param content the content which are going to published
     * @param <T> the type of content
     */
    @Override
    public <T> void publish(final IEventType eventType, final T content) {
        final Message<T> message = new Message<>(eventType, content);
        publish(message);
    }
}
