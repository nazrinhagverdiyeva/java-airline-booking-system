# ✈️ Airline Booking System

A Java console application for managing flights and ticket bookings, built as an Object-Oriented Programming course project (Group 24.1). It demonstrates abstraction, inheritance, polymorphism, composition and the **Observer design pattern**.


## Features
- Add flights and search them by origin and destination
- Book and cancel tickets, each with a unique booking ID
- Two ticket classes (Economy and Business) with their own price calculation
- Discounts applied to ticket prices
- Domestic and international flights
- Real-time notifications (email and SMS) when a booking is confirmed or cancelled, or a flight status changes
- Reports: list all flights and all tickets, find tickets by passenger

## OOP concepts applied
| Concept | How it is used |
|---|---|
| Abstraction | `Ticket` is an abstract class; interfaces `Bookable`, `Priceable`, `Flyable` and `BookingObserver` define contracts |
| Inheritance | `EconomyTicket` and `BusinessTicket` extend `Ticket`; `DomesticFlight` and `InternationalFlight` extend `Flight` |
| Polymorphism | Each ticket class implements its own `calculatePrice()` and `getPriceCategory()` |
| Composition | A `Ticket` contains a `Flight` and a `Passenger` |
| Encapsulation | Private fields with getters and setters |

## Design pattern: Observer
`BookingService` keeps a list of `BookingObserver` objects and notifies them when something changes:

```
BookingService (subject)  →  BookingObserver (interface)
                                 ├── EmailNotifier
                                 └── SmsNotifier
```

New notification channels can be added without changing `BookingService`.

## Project structure
```
src/main/java/airline/
 ├── model/     Ticket, EconomyTicket, BusinessTicket, Flight,
 │              DomesticFlight, InternationalFlight, Passenger,
 │              Bookable, Priceable, Flyable
 ├── service/   BookingService
 ├── pattern/   BookingObserver, EmailNotifier, SmsNotifier
 └── Main.java
```

## How to run
1. Clone the repository
```bash
   git clone https://github.com/nazrinhagverdiyeva/java-airline-booking-system.git
```
2. Open it in IntelliJ IDEA
3. Run `Main.java`

## Tech stack
Java · IntelliJ IDEA · OOP

## What I learned
Designing a multi-package Java application with abstract classes and interfaces, applying the Observer pattern, and working in a team of three on a shared codebase.
