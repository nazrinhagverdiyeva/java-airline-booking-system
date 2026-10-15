package airline.pattern;

import airline.model.Ticket;

/**
 * Email bildirişi göndərən müşahidəçi.
 * Observer Pattern tətbiqi.
 */
public class EmailNotifier implements BookingObserver {

    @Override
    public void onBookingConfirmed(Ticket ticket) {
        System.out.println("[EMAIL] Hörmətli " + ticket.getPassenger().getFullName()
                + ", biletiniz təsdiqləndi! Rezervasiya kodu: " + ticket.getBookingId()
                + ". Email: " + ticket.getPassenger().getEmail());
    }

    @Override
    public void onBookingCancelled(Ticket ticket) {
        System.out.println("[EMAIL] Hörmətli " + ticket.getPassenger().getFullName()
                + ", " + ticket.getBookingId() + " nömrəli rezervasiyanız ləğv edildi.");
    }

    @Override
    public void onFlightStatusChanged(String flightNumber, String newStatus) {
        System.out.println("[EMAIL] Uçuş " + flightNumber
                + " üçün yeni vəziyyət bildirişi: " + newStatus);
    }
}
