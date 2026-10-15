package airline.service;

import airline.model.*;
import airline.pattern.BookingObserver;

import java.util.*;

/**
 * Əsas xidmət sinfi: Rezervasiya əməliyyatlarını idarə edir.
 * Observer Pattern ilə müşahidəçilərə bildiriş göndərir.
 */
public class BookingService {

    // Uçuşlar və biletlər siyahıları
    private final Map<String, Flight>  flights  = new HashMap<>();
    private final Map<String, Ticket>  tickets  = new HashMap<>();
    private final List<BookingObserver> observers = new ArrayList<>();

    // ── Observer əlavə et / sil ──────────────────────────────────────────────
    public void addObserver(BookingObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(BookingObserver observer) {
        observers.remove(observer);
    }

    // ── Bildiriş göndər ──────────────────────────────────────────────────────
    private void notifyBookingConfirmed(Ticket ticket) {
        for (BookingObserver o : observers) o.onBookingConfirmed(ticket);
    }

    private void notifyBookingCancelled(Ticket ticket) {
        for (BookingObserver o : observers) o.onBookingCancelled(ticket);
    }

    private void notifyFlightStatusChanged(String flightNumber, String status) {
        for (BookingObserver o : observers) o.onFlightStatusChanged(flightNumber, status);
    }

    // ── Uçuş əməliyyatları ──────────────────────────────────────────────────
    public void addFlight(Flight flight) {
        flights.put(flight.getFlightNumber(), flight);
        System.out.println("Uçuş əlavə edildi: " + flight);
    }

    public Optional<Flight> findFlight(String flightNumber) {
        return Optional.ofNullable(flights.get(flightNumber));
    }

    public List<Flight> searchFlights(String origin, String destination) {
        List<Flight> result = new ArrayList<>();
        for (Flight f : flights.values()) {
            if (f.getOrigin().equalsIgnoreCase(origin)
                    && f.getDestination().equalsIgnoreCase(destination)) {
                result.add(f);
            }
        }
        return result;
    }

    public void updateFlightStatus(String flightNumber, String newStatus) {
        flights.computeIfPresent(flightNumber, (k, f) -> {
            f.setStatus(newStatus);
            notifyFlightStatusChanged(flightNumber, newStatus);
            return f;
        });
    }

    // ── Bilet əməliyyatları ─────────────────────────────────────────────────
    public Optional<Ticket> bookTicket(Ticket ticket) {
        if (ticket.book(ticket.getPassenger())) {
            tickets.put(ticket.getBookingId(), ticket);
            notifyBookingConfirmed(ticket);
            return Optional.of(ticket);
        }
        return Optional.empty();
    }

    public boolean cancelTicket(String bookingId) {
        Ticket ticket = tickets.get(bookingId);
        if (ticket != null && ticket.cancel(bookingId)) {
            notifyBookingCancelled(ticket);
            tickets.remove(bookingId);
            return true;
        }
        return false;
    }

    public Optional<Ticket> findTicket(String bookingId) {
        return Optional.ofNullable(tickets.get(bookingId));
    }

    public List<Ticket> getTicketsByPassenger(String passengerId) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket t : tickets.values()) {
            if (t.getPassenger().getPassengerId().equals(passengerId)) {
                result.add(t);
            }
        }
        return result;
    }

    // ── Hesabat ─────────────────────────────────────────────────────────────
    public void printAllFlights() {
        System.out.println("\n══════════════════════════════════════");
        System.out.println("     BÜTÜN UÇUŞLAR");
        System.out.println("══════════════════════════════════════");
        flights.values().forEach(System.out::println);
    }

    public void printAllTickets() {
        System.out.println("\n══════════════════════════════════════");
        System.out.println("     BÜTÜN BİLETLƏR");
        System.out.println("══════════════════════════════════════");
        tickets.values().forEach(System.out::println);
    }
}
