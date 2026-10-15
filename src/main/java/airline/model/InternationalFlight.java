package airline.model;

import java.time.LocalDateTime;

/**
 * Beynəlxalq uçuş — Flight sinfindən miras alır (inheritance).
 */
public class InternationalFlight extends Flight {

    private final String visaRequirement; // "VİZA TƏLƏBİ YOX", "VİZA LAZIMDIR" və s.
    private final String airline;

    public InternationalFlight(String flightNumber, String origin, String destination,
                               LocalDateTime departureTime, LocalDateTime arrivalTime,
                               int totalSeats, String visaRequirement, String airline) {
        super(flightNumber, origin, destination, departureTime, arrivalTime, totalSeats);
        this.visaRequirement = visaRequirement;
        this.airline         = airline;
    }

    @Override
    public String getFlightType() {
        return "BEYNƏLXALQ UÇUŞ (" + airline + ")";
    }

    public String getVisaRequirement() { return visaRequirement; }
    public String getAirline()         { return airline; }
}
