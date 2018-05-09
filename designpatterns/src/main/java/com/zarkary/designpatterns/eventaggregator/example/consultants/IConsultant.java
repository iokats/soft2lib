package com.zarkary.designpatterns.eventaggregator.example.consultants;

/**
 * The interface that describes the behavior and the characteristics of
 * a consultant entity.
 *
 * @author Ioannis Katsatos
 * @since 23.04.2018
 */
public interface IConsultant {

    void setCurrentLocation(Location currentLocation);

    void setAvailableAtHome(boolean availableAtHome);

    String getName();

    Location getCurrentLocation();

    boolean isAvailable();
}
