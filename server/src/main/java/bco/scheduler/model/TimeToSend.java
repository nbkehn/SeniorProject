/**
 * Time to send enum
 * @author Noah Trimble
 */
package bco.scheduler.model;

public enum TimeToSend {
    ONE_WEEK_PRIOR("One Week Prior", -7),
    ONE_DAY_PRIOR("One Day Prior", -1),
    ONE_YEAR_AFTER("One Year After", 365),
    OTW("OTW Message (Only text template will be used)", -1000),
    APPOINTMENT_CREATION("Appointment Creation Message", -1001);

    // Name of time to send
    private String name;

    // Offset in days of when to send
    private int offset;

    /**
     * CommunicationType Constructor with name
     * @param name Name of time to send
     * @param offset Offset of when to send
     */
    TimeToSend(String name, int offset) {
        this.name = name;
        this.offset = offset;
    }

    /**
     * Get name of communication type
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get offset of when to send in days from
     * the start of an appointment
     * @return offset
     */
    public int getOffset() {
        return offset;
    }
}

