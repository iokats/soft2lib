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
 * In the Publisher/Subscriber pattern a publisher (entity responsible for
 * publishing a message) publishes a message and there are one or more
 * Subscribers who capture the published message.
 *
 * @author Ioannis Katsatos
 * @since 30.03.2018
 */
public interface IPublisher {

    /**
     * This method is responsible for publishing {@link IMessage} of various content types.
     *
     * <p>Each {@link IMessage} consists of two basic information. The {@link IEventType}
     * in which belongs to and the content which transfer the information. </p>
     *
     * @param message the message which is going to be published
     * @param <T> the content type of message
     */
    <T> void publish(IMessage<T> message);

    /**
     * This method is able to publish, for a specific {@link IEventType}, different types
     * of content.
     *
     * @param eventType the type of event
     * @param content the content which is going to be published
     * @param <T> the content type of content
     */
    <T> void publish(IEventType eventType, T content);
}