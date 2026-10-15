package airline.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Mücərrəd sinif: Bilet növlərinin əsasını təşkil edir.
 * Bookable və Priceable interfeyslərlə işləyir.
 * Kompozisiya: Flight və Passenger obyektlərini ehtiva edir.
 */
public abstract class Ticket implements Bookable, Priceable {

    private final String bookingId;
    private final Flight flight;         // KOMPOZİSİYA — Ticket içərisində Flight var
    private final Passenger passenger;   // KOMPOZİSİYA — Ticket içərisində Passenger var
    private final LocalDateTime bookingDate;
    private boolean isConfirmed;
    private String seatNumber;

    public Ticket(Flight flight, Passenger passenger) {
        this.bookingId   = "BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.flight      = flight;
        this.passenger   = passenger;
        this.bookingDate = LocalDateTime.now();
        this.isConfirmed = false;
    }

    // Abstract metod: hər bilet sinfi qiyməti özü hesablayır
    @Override
    public abstract double calculatePrice();

    @Override
    public abstract String getPriceCategory();

    // Bookable metodları
    @Override
    public boolean book(Passenger passenger) {
        if (flight.reserveSeat()) {
            this.isConfirmed = true;
            System.out.println("Bilet təsdiqləndi: " + bookingId + " | " + passenger.getFullName());
            return true;
        }
        System.out.println("Üzr istəyirik, boş yer yoxdur.");
        return false;
    }

    @Override
    public boolean cancel(String bookingId) {
        if (this.bookingId.equals(bookingId) && isConfirmed) {
            this.isConfirmed = false;
            flight.releaseSeat();
            System.out.println("Rezervasiya ləğv edildi: " + bookingId);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAvailable() {
        return flight.getAvailableSeats() > 0;
    }

    @Override
    public double applyDiscount(double discountPercent) {
        double basePrice = calculatePrice();
        return basePrice - (basePrice * discountPercent / 100);
    }

    // Getters
    public String getBookingId()         { return bookingId; }
    public Flight getFlight()            { return flight; }
    public Passenger getPassenger()      { return passenger; }
    public LocalDateTime getBookingDate(){ return bookingDate; }
    public boolean isConfirmed()         { return isConfirmed; }
    public String getSeatNumber()        { return seatNumber; }
    public void setSeatNumber(String s)  { this.seatNumber = s; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket t = (Ticket) o;
        return Objects.equals(bookingId, t.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }

    @Override
    public String toString() {
        return String.format("Bilet[%s] | Uçuş: %s | Sərnişin: %s | Qiymət: %.2f AZN | Kateqoriya: %s | Təsdiq: %s",
                bookingId,
                flight.getFlightNumber(),
                passenger.getFullName(),
                calculatePrice(),
                getPriceCategory(),
                isConfirmed ? "BƏLİ" : "XEYİR");
    }
}
