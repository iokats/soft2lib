package com.zarkary.designpatterns.eventaggregator.example.consultants.framework;

import com.zarkary.designpatterns.eventaggregator.example.consultants.IConsultant;
import com.zarkary.designpatterns.eventaggregator.example.consultants.Location;

/**
 * Consultant implementation. In this example we are using the EventAggregator
 * pattern directly from the framework, without any further modification.
 *
 * <p>We have a pure domain object, independent and agnostic of the EventAggregator
 * design pattern.</p>
 *
 * @author Ioannis Katsatos
 * @since 23.04.2018
 */
public class Consultant implements IConsultant {

    private String name;
    private Location currentLocation = Location.OTHER;
    private boolean availableAtHome;

    /**
     * Construct a new {@link IConsultant} object, with the given name
     * @param name of the consultant
     */
    public Consultant(String name) {
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
        return (location != null && location.equals("Home")) ? availableAtHome : true;
    }
}
