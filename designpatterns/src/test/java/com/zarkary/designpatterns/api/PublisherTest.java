package com.zarkary.designpatterns.api;

import com.zarkary.designpatterns.eventaggregator.api.EventAggregator;
import com.zarkary.designpatterns.eventaggregator.api.IEventType;
import com.zarkary.designpatterns.eventaggregator.api.IMessage;
import com.zarkary.designpatterns.eventaggregator.api.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PublisherTest {

    @Mock
    private EventAggregator eventAggregator;

    private Publisher publisher;

    @Before
    public void setUp() {

        publisher = new Publisher(eventAggregator);
    }

    @Test(expected = NullPointerException.class)
    public void publisher_whenEventAggregatorIsNull_thenNullPointerException() {

        // Arrange
        final EventAggregator nullEventAggregator = null;

        // Act
        new Publisher(nullEventAggregator);

        // Assert
    }

    @Test
    public void publish_whenPassedMessageIsNull_thenMessageIsPublishedNormally() {

        // Arrange
        final IMessage nullMessage = null;

        // Act
        publisher.publish(nullMessage);

        // Assert
        verify(eventAggregator, times(1)).publish(null);
    }

    @Test
    public void publish_whenPassedMessageIsNotNull_thenMessageIsPublishedNormally() {

        // Arrange
        final IMessage notNullMessage = mock(IMessage.class);

        // Act
        publisher.publish(notNullMessage);

        // Assert
        verify(eventAggregator, times(1)).publish(notNullMessage);
    }

    @Test(expected = NullPointerException.class)
    public void publish_whenPassedEventTypeIsNull_thenNullPointerException() {
        // Arrange
        final IEventType nullEventType = null;
        final String someContent = "Some Content";

        // Act
        publisher.publish(nullEventType, someContent);

        // Assert
    }

    @Test(expected = NullPointerException.class)
    public void publish_whenPassedContentIsNull_thenNullPointerException() {

        // Arrange
        final IEventType someEventType = mock(IEventType.class);
        final String nullContent = null;

        // Act
        publisher.publish(someEventType, nullContent);

        // Assert
    }

    @Test
    public void publish_whenPassedEventTypeAndContentAreNotNull_thenMessageIsPublishedNormally() {

        // Arrange
        final IEventType someEventType = mock(IEventType.class);
        final String someContent = "Some Content";

        // Act
        publisher.publish(someEventType, someContent);

        // Assert
        verify(eventAggregator, times(1)).publish(any());
    }
}
