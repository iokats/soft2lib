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
