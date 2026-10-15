package airline.model;

/**
 * Ekonom sinif bileti.
 */
public class EconomyTicket extends Ticket {

    private static final double BASE_PRICE = 150.0; // AZN

    public EconomyTicket(Flight flight, Passenger passenger) {
        super(flight, passenger);
    }

    @Override
    public double calculatePrice() {
        double price = BASE_PRICE;
        // Uşaq üçün 30% endirim, yaşlı üçün 20% endirim
        if (getPassenger().getType() == Passenger.PassengerType.CHILD) {
            price = applyDiscountInternal(price, 30);
        } else if (getPassenger().getType() == Passenger.PassengerType.SENIOR) {
            price = applyDiscountInternal(price, 20);
        }
        return price;
    }

    @Override
    public String getPriceCategory() {
        return "EKONOM";
    }

    private double applyDiscountInternal(double price, double percent) {
        return price - (price * percent / 100);
    }
}
