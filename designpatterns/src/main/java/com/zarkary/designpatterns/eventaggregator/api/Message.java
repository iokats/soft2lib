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
 * Implementation of the {@link IMessage} interface.
 *
 * @param <T> the data type of content
 *
 * @author Ioannis Katsatos
 * @since 02.04.2018
 */
public class Message<T> implements IMessage<T> {

    private final IEventType eventType;
    private final T content;

    /**
     * Constructor
     *
     * @param eventType the category/subject of message
     * @param content the content of message
     */
    public Message(final IEventType eventType, final T content) {
        this.eventType = requireNonNull(eventType);
        this.content = requireNonNull(content);
    }

    /**
     * @return the {@link IEventType} of this message
     */
    @Override
    public IEventType getEventType() {
        return eventType;
    }

    /**
     * @return the content of this message
     */
    @Override
    public T getContent() {
        return content;
    }
}
