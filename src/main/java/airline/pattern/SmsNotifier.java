package airline.pattern;

import airline.model.Ticket;

/**
 * SMS bildirişi göndərən müşahidəçi.
 * Observer Pattern tətbiqi.
 */
public class SmsNotifier implements BookingObserver {

    @Override
    public void onBookingConfirmed(Ticket ticket) {
        System.out.println("[SMS] Biletiniz hazırdır. Kod: "
                + ticket.getBookingId() + " | Uçuş: "
                + ticket.getFlight().getFlightNumber());
    }

    @Override
    public void onBookingCancelled(Ticket ticket) {
        System.out.println("[SMS] Rezervasiya ləğv edildi: " + ticket.getBookingId());
    }

    @Override
    public void onFlightStatusChanged(String flightNumber, String newStatus) {
        System.out.println("[SMS] Uçuş " + flightNumber + ": " + newStatus);
    }
}
