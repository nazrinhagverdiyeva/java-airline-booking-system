package airline.model;

import java.util.Objects;

/**
 * Sərnişin məlumatlarını saxlayan sinif.
 */
public class Passenger {

    private final String passengerId;
    private String firstName;
    private String lastName;
    private String email;
    private String passportNumber;
    private PassengerType type; // ADULT, CHILD, SENIOR

    public enum PassengerType {
        ADULT, CHILD, SENIOR
    }

    public Passenger(String passengerId, String firstName, String lastName,
                     String email, String passportNumber, PassengerType type) {
        this.passengerId    = passengerId;
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.email          = email;
        this.passportNumber = passportNumber;
        this.type           = type;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Getters & Setters
    public String getPassengerId()      { return passengerId; }
    public String getFirstName()        { return firstName; }
    public String getLastName()         { return lastName; }
    public String getEmail()            { return email; }
    public String getPassportNumber()   { return passportNumber; }
    public PassengerType getType()      { return type; }
    public void setEmail(String email)  { this.email = email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        Passenger p = (Passenger) o;
        return Objects.equals(passengerId, p.passengerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerId);
    }

    @Override
    public String toString() {
        return String.format("Sərnişin[%s]: %s | Tip: %s", passengerId, getFullName(), type);
    }
}
