package airline.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Mücərrəd sinif: Bütün uçuş növlərinin əsasını təşkil edir.
 * Flyable interfeysini tətbiq edir.
 */
public abstract class Flight implements Flyable {

    private final String flightNumber;   // Uçuş nömrəsi
    private final String origin;         // Çıxış şəhəri
    private final String destination;    // Təyinat şəhəri
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;
    private int totalSeats;
    private int bookedSeats;
    private String status; // "SCHEDULED", "BOARDING", "IN_AIR", "LANDED", "CANCELLED"

    public Flight(String flightNumber, String origin, String destination,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, int totalSeats) {
        this.flightNumber   = flightNumber;
        this.origin         = origin;
        this.destination    = destination;
        this.departureTime  = departureTime;
        this.arrivalTime    = arrivalTime;
        this.totalSeats     = totalSeats;
        this.bookedSeats    = 0;
        this.status         = "SCHEDULED";
    }

    // Abstract metod: hər uçuş növü özü tətbiq edir
    public abstract String getFlightType();

    // Flyable metodlarının tətbiqi
    @Override
    public void takeOff() {
        this.status = "IN_AIR";
        System.out.println(flightNumber + " uçuşu havaya qalxdı.");
    }

    @Override
    public void land() {
        this.status = "LANDED";
        System.out.println(flightNumber + " uçuşu endi.");
    }

    @Override
    public String getFlightStatus() {
        return status;
    }

    // Boş yer sayı
    public int getAvailableSeats() {
        return totalSeats - bookedSeats;
    }

    public boolean reserveSeat() {
        if (bookedSeats < totalSeats) {
            bookedSeats++;
            return true;
        }
        return false;
    }

    public boolean releaseSeat() {
        if (bookedSeats > 0) {
            bookedSeats--;
            return true;
        }
        return false;
    }

    // Getters
    public String getFlightNumber()      { return flightNumber; }
    public String getOrigin()            { return origin; }
    public String getDestination()       { return destination; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public LocalDateTime getArrivalTime()   { return arrivalTime; }
    public int getTotalSeats()           { return totalSeats; }
    public int getBookedSeats()          { return bookedSeats; }
    public String getStatus()            { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return Objects.equals(flightNumber, flight.flightNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s → %s | %s | Boş yer: %d/%d | Vəziyyət: %s",
                flightNumber, origin, destination, getFlightType(),
                getAvailableSeats(), totalSeats, status);
    }
}
