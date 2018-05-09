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
