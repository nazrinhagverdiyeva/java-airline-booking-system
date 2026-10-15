package airline.model;

import java.time.LocalDateTime;

/**
 * Daxili uçuş — Flight sinfindən miras alır (inheritance).
 */
public class DomesticFlight extends Flight {

    private final String region; // məs. "Şimal", "Cənub"

    public DomesticFlight(String flightNumber, String origin, String destination,
                          LocalDateTime departureTime, LocalDateTime arrivalTime,
                          int totalSeats, String region) {
        super(flightNumber, origin, destination, departureTime, arrivalTime, totalSeats);
        this.region = region;
    }

    @Override
    public String getFlightType() {
        return "DAXİLİ UÇUŞ (" + region + ")";
    }

    public String getRegion() {
        return region;
    }
}
