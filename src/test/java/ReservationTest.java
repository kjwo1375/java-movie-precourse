// src/test/java/ReservationTest.java
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class ReservationTest {

    @Test
    void getFinalPriceShouldCalculateCorrectlyWithAllDiscounts() {
        Discount discount = new Discount();
        Reservation reservation = new Reservation(discount);

        Movie movieA = new Movie("Movie A", 100);
        Seat seatA1 = new Seat("A", 1, "S"); // 18000
        List<Seat> seatsA = new ArrayList<>();
        seatsA.add(seatA1);
        Screening screeningA = new Screening(movieA, LocalDateTime.of(2025, 1, 1, 10, 0), seatsA);

        Movie movieB = new Movie("Movie B", 100);
        Seat seatB1 = new Seat("B", 1, "A"); // 15000
        List<Seat> seatsB = new ArrayList<>();
        seatsB.add(seatB1);
        Screening screeningB = new Screening(movieB, LocalDateTime.of(2025, 1, 1, 14, 0), seatsB);

        reservation.addScreening(screeningA);
        reservation.addScreening(screeningB);

        reservation.selectSeat(screeningA, "A", 1);
        reservation.selectSeat(screeningB, "B", 1);

        int day = 10;
        int time = 10;
        int points = 1000;
        String paymentMethod = "card";

        // 1. Base Price: 33000
        // 2. MovieDay (10%): 33000 - 3300 = 29700
        // 3. Time (< 11): 29700 - 2000 = 27700
        // 4. Points: 27700 - 1000 = 26700
        // 5. Card (5%): 26700 - 1335 = 25365
        int finalPrice = reservation.getFinalPrice(day, time, points, paymentMethod);

        assertThat(finalPrice).isEqualTo(25365);
    }

    @Test
    void getFinalPriceShouldCalculateCorrectlyWithNoDiscounts() {
        Discount discount = new Discount();
        Reservation reservation = new Reservation(discount);

        Movie movieA = new Movie("Movie A", 100);
        Seat seatA1 = new Seat("A", 1, "S"); // 18000
        List<Seat> seatsA = new ArrayList<>();
        seatsA.add(seatA1);
        Screening screeningA = new Screening(movieA, LocalDateTime.of(2025, 1, 1, 14, 0), seatsA);

        reservation.addScreening(screeningA);
        reservation.selectSeat(screeningA, "A", 1);

        int day = 11;
        int time = 14;
        int points = 0;
        String paymentMethod = "other";

        int finalPrice = reservation.getFinalPrice(day, time, points, paymentMethod);

        assertThat(finalPrice).isEqualTo(18000);
    }

    @Test
    void selectSeatShouldIgnoreAlreadyOccupiedSeat() {
        Discount discount = new Discount();
        Reservation reservation = new Reservation(discount);

        Movie movieA = new Movie("Movie A", 100);
        Seat seatA1 = new Seat("A", 1, "S"); // 18000
        seatA1.occupy(); // Pre-occupy the seat
        List<Seat> seatsA = new ArrayList<>();
        seatsA.add(seatA1);
        Screening screeningA = new Screening(movieA, LocalDateTime.of(2025, 1, 1, 14, 0), seatsA);

        reservation.addScreening(screeningA);
        reservation.selectSeat(screeningA, "A", 1); // This should fail and return null

        int finalPrice = reservation.getFinalPrice(11, 14, 0, "other");

        assertThat(finalPrice).isEqualTo(0);
    }

    @Test
    void selectSeatShouldIgnoreNonExistentSeat() {
        Discount discount = new Discount();
        Reservation reservation = new Reservation(discount);

        Movie movieA = new Movie("Movie A", 100);
        Seat seatA1 = new Seat("A", 1, "S"); // 18000
        List<Seat> seatsA = new ArrayList<>();
        seatsA.add(seatA1);
        Screening screeningA = new Screening(movieA, LocalDateTime.of(2025, 1, 1, 14, 0), seatsA);

        reservation.addScreening(screeningA);
        reservation.selectSeat(screeningA, "Z", 99); // This should fail

        int finalPrice = reservation.getFinalPrice(11, 14, 0, "other");

        assertThat(finalPrice).isEqualTo(0);
    }
}