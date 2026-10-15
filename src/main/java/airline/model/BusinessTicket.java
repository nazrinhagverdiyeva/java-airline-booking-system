package airline.model;

/**
 * Biznes sinif bileti.
 */
public class BusinessTicket extends Ticket {

    private static final double BASE_PRICE = 450.0; // AZN
    private boolean loungeAccess; // Salon girişi

    public BusinessTicket(Flight flight, Passenger passenger, boolean loungeAccess) {
        super(flight, passenger);
        this.loungeAccess = loungeAccess;
    }

    @Override
    public double calculatePrice() {
        double price = BASE_PRICE;
        if (loungeAccess) {
            price += 50.0; // Salon girişi üçün əlavə ödəniş
        }
        if (getPassenger().getType() == Passenger.PassengerType.SENIOR) {
            price = price - (price * 10 / 100); // Yaşlılara 10% endirim
        }
        return price;
    }

    @Override
    public String getPriceCategory() {
        return "BİZNES";
    }

    public boolean hasLoungeAccess() { return loungeAccess; }
}
