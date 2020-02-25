/**
 * User type enum
 * @author Noah Trimble
 */
package bco.scheduler.model;

public enum UserType {
    CUSTOMER("Customer"),
    RSA("RSA"),
    TECHNICIAN("Technician");

    // Name of user type
    private String name;

    /**
     * UserType Constructor with name
     * @param name Name of user type
     */
    UserType(String name) {
        this.name = name;
    }

    /**
     * Get name of user type
     * @return name
     */
    public String getName() {
        return name;
    }
}

