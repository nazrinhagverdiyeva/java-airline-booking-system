package airline.model;

/**
 * Interface: Bilet rezervasiyasına aid əməliyyatları təsvir edir.
 */
public interface Bookable {
    boolean book(Passenger passenger);
    boolean cancel(String bookingId);
    boolean isAvailable();
}
