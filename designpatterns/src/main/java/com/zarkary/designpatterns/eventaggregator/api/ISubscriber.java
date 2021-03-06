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

/**
 * A subscriber captures messages of the {@link IEventType} it is interested in.
 *
 * <p>Technically a Subscriber is a generic class that allows creation of multiple
 * instances of a Subscriber and each Subscriber subscribes to the IMessages it is
 * interested in using {@link EventAggregator}.</p>
 *
 * <p>The Subscriber binds together an {@link IEventType} with an {@link IAction}.
 * When a {@link IPublisher} publishes a {@link IMessage}, with the Subscriber's
 * event type, then the EventAggregator executes the IAction of the Subscriber.</p>
 *
 * @author Ioannis Katsatos
 * @since 03.04.2018
 */
public interface ISubscriber {

    /**
     * Subscribes an {@link IEventType} which is interested in and bind it with
     * the {@link IAction} which will be executed, when an {@link IMessage} of
     * the given IEventType is published.
     *
     * @param eventType the {@link IEventType} which the Subscriber is interested in
     * @param action    the {@link IAction} which will be executed when an event of
     *                  the given IEventType take place
     * @param <T> the type of {@link IAction} content
     */
    <T> void subscribe(IEventType eventType, IAction<T> action);

    /**
     * Unsubscribe an {@link IEventType} for which the Subscriber is not interested
     * any more.
     *
     * @param eventType the {@link IEventType} which the Subscriber is not interested
     *                  any more.
     */
    void unsubscribe(IEventType eventType);

    /**
     * Place holder for releasing all the resources which are used from this entity.
     */
    void dispose();
}
