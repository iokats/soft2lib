package com.zarkary.designpatterns.eventaggregator.example.consultants.framework;

import com.zarkary.designpatterns.eventaggregator.example.consultants.Event;
import com.zarkary.designpatterns.eventaggregator.example.consultants.Location;
import com.zarkary.designpatterns.eventaggregator.example.consultants.MsgMovement;
import com.zarkary.designpatterns.eventaggregator.api.*;

/**
 * Test Application
 *
 * @author Ioannis Katsatos
 * @since 23.04.2018
 */
public class App {

    public static void main(String[] args) {

        EventAggregator eventAggregator = new EventAggregator();

        Consultant john = new Consultant("John");
        Consultant simony = new Consultant("Simony");
        Consultant peter = new Consultant("Peter");

        ISubscriber subscriber = new Subscriber(eventAggregator);
        subscriber.subscribe(Event.LOCATION_CHANGED, message -> {
            final MsgMovement msgMovement = ((MsgMovement)message.getContent());
            msgMovement.getConsultant().setCurrentLocation(msgMovement.getPlace());
        });

        subscriber.subscribe(Event.AVAILABILITY_CHANGED, message -> {
            final MsgMovement msgMovement = ((MsgMovement)message.getContent());
            msgMovement.getConsultant().setAvailableAtHome(msgMovement.isAvailableAtHome());
        });

        System.out.println("\n****************************************************");

        System.out.println(john.getName() + " currentLocation: "
                + john.getCurrentLocation() + " is available: " + john.isAvailable());
        System.out.println(simony.getName() + " currentLocation: "
                + simony.getCurrentLocation() + " is available: " + simony.isAvailable());
        System.out.println(peter.getName() + " currentLocation: "
                + peter.getCurrentLocation() + " is available: " + peter.isAvailable());

        System.out.println("\n****************************************************");

        final IPublisher publisher = new Publisher(eventAggregator);

        publisher.publish(Event.LOCATION_CHANGED, new MsgMovement(john, Location.TRAIN_STATION));
        publisher.publish(Event.LOCATION_CHANGED, new MsgMovement(peter, Location.HOME));

        System.out.println(john.getName() + " currentLocation: "
                + john.getCurrentLocation() + " is available: " + john.isAvailable());
        System.out.println(simony.getName() + " currentLocation: "
                + simony.getCurrentLocation() + " is available: " + simony.isAvailable());
        System.out.println(peter.getName() + " currentLocation: "
                + peter.getCurrentLocation() + " is available: " + peter.isAvailable());

        System.out.println("\n****************************************************");

        publisher.publish(Event.LOCATION_CHANGED, new MsgMovement(simony, Location.OFFICE));
        publisher.publish(Event.AVAILABILITY_CHANGED, new MsgMovement(peter, true));

        System.out.println(john.getName() + " currentLocation: "
                + john.getCurrentLocation() + " is available: " + john.isAvailable());
        System.out.println(simony.getName() + " currentLocation: "
                + simony.getCurrentLocation() + " is available: " + simony.isAvailable());
        System.out.println(peter.getName() + " currentLocation: "
                + peter.getCurrentLocation() + " is available: " + peter.isAvailable());

        System.out.println("\n****************************************************");
    }
}
