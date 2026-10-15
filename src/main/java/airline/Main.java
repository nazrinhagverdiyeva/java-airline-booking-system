package airline;

import airline.model.*;
import airline.pattern.*;
import airline.service.BookingService;

import java.time.LocalDateTime;

/**
 * Aviakompaniya Rezervasiya Sistemi — Demo
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║  AVİAKOMPANİYA REZERVASİYA SİSTEMİ  ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        // ── Xidmət obyekti yarat ────────────────────────────────────────────
        BookingService service = new BookingService();

        // ── Observer Pattern: Müşahidəçiləri əlavə et ──────────────────────
        service.addObserver(new EmailNotifier());
        service.addObserver(new SmsNotifier());

        // ── Uçuşlar yarat ───────────────────────────────────────────────────
        Flight baku_istanbul = new InternationalFlight(
                "J2-101",
                "Bakı", "İstanbul",
                LocalDateTime.of(2025, 6, 15, 8, 0),
                LocalDateTime.of(2025, 6, 15, 10, 30),
                120,
                "VİZA TƏLƏBİ YOX",
                "AZAL"
        );

        Flight baku_ganja = new DomesticFlight(
                "AZ-55",
                "Bakı", "Gəncə",
                LocalDateTime.of(2025, 6, 15, 14, 0),
                LocalDateTime.of(2025, 6, 15, 15, 0),
                60,
                "Qərb"
        );

        service.addFlight(baku_istanbul);
        service.addFlight(baku_ganja);
        service.printAllFlights();

        // ── Sərnişinlər yarat ───────────────────────────────────────────────
        Passenger ali = new Passenger(
                "P001", "Əli", "Həsənov",
                "ali@email.az", "AA1234567",
                Passenger.PassengerType.ADULT
        );

        Passenger leyla = new Passenger(
                "P002", "Leyla", "Məmmədova",
                "leyla@email.az", "BB7654321",
                Passenger.PassengerType.SENIOR
        );

        Passenger cocuq = new Passenger(
                "P003", "Aytac", "Əliyeva",
                "ana@email.az", "CC1111111",
                Passenger.PassengerType.CHILD
        );

        // ── Biletlər yarat (kompozisiya) ────────────────────────────────────
        Ticket aliTicket   = new BusinessTicket(baku_istanbul, ali, true);
        Ticket leylaTicket = new EconomyTicket(baku_istanbul, leyla);
        Ticket cocuqTicket = new EconomyTicket(baku_ganja, cocuq);

        System.out.println("\n── Bilet Alışı ─────────────────────────────");
        service.bookTicket(aliTicket);
        service.bookTicket(leylaTicket);
        service.bookTicket(cocuqTicket);

        service.printAllTickets();

        // ── Uçuş axtarışı ──────────────────────────────────────────────────
        System.out.println("\n── Uçuş Axtarışı: Bakı → İstanbul ─────────");
        service.searchFlights("Bakı", "İstanbul")
               .forEach(System.out::println);

        // ── Uçuş vəziyyətini dəyiş (Observer işə düşür) ────────────────────
        System.out.println("\n── Uçuş Vəziyyəti Yenilənir ────────────────");
        service.updateFlightStatus("J2-101", "BOARDING");

        // ── Rezervasiyanı ləğv et ──────────────────────────────────────────
        System.out.println("\n── Rezervasiya Ləğvi ───────────────────────");
        service.cancelTicket(leylaTicket.getBookingId());

        // ── Qiymət hesablaması nümunələri ──────────────────────────────────
        System.out.println("\n── Qiymət Hesablaması ──────────────────────");
        System.out.printf("Əli (Biznes + Salon): %.2f AZN%n",  aliTicket.calculatePrice());
        System.out.printf("Leyla (Ekonom, Yaşlı): %.2f AZN%n", leylaTicket.calculatePrice());
        System.out.printf("Aytac (Ekonom, Uşaq): %.2f AZN%n",  cocuqTicket.calculatePrice());
        System.out.printf("Əliyə 10%% endirim: %.2f AZN%n",    aliTicket.applyDiscount(10));

        // ── Uçuşun hərəkəti ─────────────────────────────────────────────────
        System.out.println("\n── Uçuş Hərəkəti ───────────────────────────");
        baku_istanbul.takeOff();
        baku_istanbul.land();

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║        SİSTEM UĞURLA TAMAMLANDI     ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}
