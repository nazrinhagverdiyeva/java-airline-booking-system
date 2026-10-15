package airline.model;

/**
 * Interface: Qiymət hesablamasına aid əməliyyatları təsvir edir.
 */
public interface Priceable {
    double calculatePrice();
    double applyDiscount(double discountPercent);
    String getPriceCategory();
}
