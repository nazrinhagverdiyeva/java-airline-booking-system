package airline.model;

/**
 * Interface: Uçuşa aid əməliyyatları təsvir edir.
 */
public interface Flyable {
    void takeOff();
    void land();
    String getFlightStatus();
}
