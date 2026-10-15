package airline.pattern;

import airline.model.Ticket;

/**
 * DİZAYN NÜMUNƏSİ: Observer (Müşahidəçi) Pattern
 *
 * Rezervasiya hadisələrini izləmək üçün istifadə olunur.
 * Bilet alındıqda, ləğv edildikdə və ya uçuş vəziyyəti dəyişdikdə
 * bütün müşahidəçilər avtomatik xəbərdar edilir.
 */
public interface BookingObserver {
    void onBookingConfirmed(Ticket ticket);
    void onBookingCancelled(Ticket ticket);
    void onFlightStatusChanged(String flightNumber, String newStatus);
}
