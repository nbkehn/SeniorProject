/**
 * Communication type enum
 * @author Connor Parke and Noah Trimble
 */
package bco.scheduler.model;

public enum CommunicationType {
    EMAIL("Email"),
    TEXT("Text"),
    EMAIL_AND_TEXT("Email and Text");

    // Name of communication type
    private String name;

    /**
     * CommunicationType Constructor with name
     * @param String Name of communication type
     */
    CommunicationType(String name) {
        this.name = name;
    }

    /**
     * Get name of communication type
     * @return name
     */
    public String getName() {
        return name;
    }
}

