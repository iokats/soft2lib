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
