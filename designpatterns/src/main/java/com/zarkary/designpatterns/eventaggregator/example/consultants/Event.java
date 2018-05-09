package com.zarkary.designpatterns.eventaggregator.example.consultants;

import com.zarkary.designpatterns.eventaggregator.api.IEventType;

/**
 * The different types of Events, {@link MsgMovement}(s) of which are going to be
 * published from the application.
 *
 * @author Ioannis Katsatos
 * @since 23.04.2018
 */
public enum Event implements IEventType {
    LOCATION_CHANGED, AVAILABILITY_CHANGED
}
