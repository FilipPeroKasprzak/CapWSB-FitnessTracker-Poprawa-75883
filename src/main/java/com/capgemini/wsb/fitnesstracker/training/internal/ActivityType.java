package com.capgemini.wsb.fitnesstracker.training.internal;

// TODO : JavaDoc


public enum ActivityType {


    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");


    private final String displayName;

    /**
     * setting up a Name for the type of Activity.
     *
     * @param displayName - sets a name for displayName
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * displaying the name of the type of Activity.
     *
     * @return displayName - returns the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

}
