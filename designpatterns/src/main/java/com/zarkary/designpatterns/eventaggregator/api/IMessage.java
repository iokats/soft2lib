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
 * The message which is published from the {@link IPublisher}. This entity is
 * characterized from an {@link IEventType}, which describes the category/
 * subject of the content which is going to be transferred from this class.
 *
 * <p>The content of the message has a generic type. So messages of a specific
 * {@link IEventType}, can publish different types of content.</p>
 *
 * @param <T> the data type of content
 *
 * @author Ioannis Katsatos
 * @since 02.04.2018
 */
public interface IMessage<T> {

    /**
     * @return the {@link IEventType} of this message
     */
    IEventType getEventType();

    /**
     * @return the content of this message
     */
    T getContent();
}
