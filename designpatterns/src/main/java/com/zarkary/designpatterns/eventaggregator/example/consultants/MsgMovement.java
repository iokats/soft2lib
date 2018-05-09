package com.zarkary.designpatterns.eventaggregator.example.consultants;

/**
 * Message that indicates who {@link IConsultant} moved and
 * which is its {@link Location} now.
 *
 * @author Ioannis Katsatos
 * @since 23.04.2018
 */
public class MsgMovement {

    private Location place;
    private IConsultant consultant;
    private boolean availableAtHome;

    /**
     * Constructor for a message which communicates the current {@link Location}
     * of the passed {@link IConsultant}
     *
     * @param consultant who is moved
     * @param place the current {@link Location} of the {@link IConsultant}
     */
    public MsgMovement(IConsultant consultant, Location place) {
        this.place = place;
        this.consultant = consultant;
    }

    /**
     * Constructor for a message which communicates whether the passed
     * {@link IConsultant} is available at home or not.
     *
     * @param consultant who is availability is changed
     * @param availableAtHome the current availability at home
     */
    public MsgMovement(IConsultant consultant, boolean availableAtHome) {

        this.consultant = consultant;
        this.availableAtHome = availableAtHome;
    }

    /**
     * @return the current {@link Location} of the given {@link IConsultant}
     */
    public Location getPlace() {
        return place;
    }

    /**
     * @return the given {@link IConsultant}
     */
    public IConsultant getConsultant() {
        return consultant;
    }

    /**
     * @return the current availability of the given {@link IConsultant} at home
     */
    public boolean isAvailableAtHome() {
        return availableAtHome;
    }
}
