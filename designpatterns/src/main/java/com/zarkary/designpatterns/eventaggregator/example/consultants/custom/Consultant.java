package com.zarkary.designpatterns.eventaggregator.example.consultants.custom;

import com.zarkary.designpatterns.eventaggregator.api.*;
import com.zarkary.designpatterns.eventaggregator.example.consultants.Event;
import com.zarkary.designpatterns.eventaggregator.example.consultants.IConsultant;
import com.zarkary.designpatterns.eventaggregator.example.consultants.Location;
import com.zarkary.designpatterns.eventaggregator.example.consultants.MsgMovement;

/**
 * Custom consultant implementation. In this case we are using the basic
 * API of the {@link EventAggregator} framework in order to make the
 * {@link IConsultant} to behave like a {@link ISubscriber}, and as well as
 * to execute specific {@link IAction} in specific {@link IEventType}.
 *
 * <p>In this we don't have a pure domain object, the {@link IConsultant}
 * entity holds a lot of information for the EventAggregator pattern and
 * also independent of its nature.</p>
 *
 *
 * @author Ioannis Katsatos
 * @since 23.04.2018
 */
public class Consultant extends Subscriber
        implements IConsultant, IAction<MsgMovement> {

    private String name;
    private Location currentLocation = Location.OTHER;
    private boolean availableAtHome;

    /**
     * Constructor
     *
     * @param eventAggregator the passed {@link EventAggregator}
     * @param name the name of the consultant
     */
    public Consultant(EventAggregator eventAggregator, String name) {
        super(eventAggregator);
        this.name = name;
        availableAtHome = false;
    }

    /**
     * Sets the current {@link Location} of the {@link IConsultant}
     * @param currentLocation of the consultant
     */
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * Sets the current availability of the {@link IConsultant} at home
     * @param availableAtHome the current availability
     */
    public void setAvailableAtHome(boolean availableAtHome) {
        this.availableAtHome = availableAtHome;
    }

    /**
     * @return the name of the {@link IConsultant}
     */
    public String getName() {
        return name;
    }

    /**
     * @return the current {@link Location} of the {@link IConsultant}
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @return the current availability of the {@link IConsultant} at home
     */
    public boolean isAvailable() {
        return isAvailableAt(currentLocation);
    }

    private boolean isAvailableAt(Location location) {
        return (location != null && location == Location.HOME) ? availableAtHome : true;
    }

    /**
     * Subscribes the {@link IEventType} for which the {@link IConsultant} will
     * be listen for events
     *
     * @param eventType the type of event
     */
    public void subscribe(IEventType eventType) {
        subscribe(eventType, this);
    }

    /**
     * The {@link IConsultant} action for each {@link IEventType}
     *
     * @param message the {@link IMessage} argument
     */
    @Override
    public void doAction(IMessage<MsgMovement> message) {

        if(message.getContent().getConsultant() != this) return;

        if(message.getEventType() == Event.LOCATION_CHANGED) {
            actionLocationChanged(message.getContent());
        } else if(message.getEventType() == Event.AVAILABILITY_CHANGED) {
            actionAvailabilityChanged(message.getContent());
        }
    }

    private void actionLocationChanged(MsgMovement msgMovement) {
        currentLocation = msgMovement.getPlace();
    }

    private void actionAvailabilityChanged(MsgMovement msgMovement) {
        availableAtHome = msgMovement.isAvailableAtHome();
    }
}
