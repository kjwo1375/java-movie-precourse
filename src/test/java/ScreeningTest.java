// src/test/java/ScreeningTest.java
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class ScreeningTest {

    @Test
    void reserveSeatShouldOccupyUnreservedSeat() {
        Seat seatA1 = new Seat("A", 1, "S");
        List<Seat> seats = new ArrayList<>();
        seats.add(seatA1);
        
        Movie movie = new Movie("Test Movie", 120);
        Screening screening = new Screening(movie, LocalDateTime.now(), seats);

        assertThat(seatA1.isOccupied()).isFalse();

        screening.reserveSeat("A", 1);

        assertThat(seatA1.isOccupied()).isTrue();
    }

    @Test
    void reserveSeatShouldDoNothingForAlreadyOccupiedSeat() {
        Seat seatA1 = new Seat("A", 1, "S");
        seatA1.occupy();
        List<Seat> seats = new ArrayList<>();
        seats.add(seatA1);
        
        Movie movie = new Movie("Test Movie", 120);
        Screening screening = new Screening(movie, LocalDateTime.now(), seats);

        assertThat(seatA1.isOccupied()).isTrue();

        screening.reserveSeat("A", 1);

        assertThat(seatA1.isOccupied()).isTrue();
    }

    @Test
    void reserveSeatShouldDoNothingForNonExistentSeat() {
        Seat seatA1 = new Seat("A", 1, "S");
        List<Seat> seats = new ArrayList<>();
        seats.add(seatA1);
        
        Movie movie = new Movie("Test Movie", 120);
        Screening screening = new Screening(movie, LocalDateTime.now(), seats);

        screening.reserveSeat("Z", 99);

        assertThat(seatA1.isOccupied()).isFalse();
    }

    Movie testMovie = new Movie("Time Test", 100);
    List<Seat> emptySeats = new ArrayList<>();

    @Test
    void isTimeOverlapShouldReturnFalseForNonOverlappingTimes() {
        LocalDateTime timeA = LocalDateTime.of(2025, 1, 1, 12, 0);
        Screening screeningA = new Screening(testMovie, timeA, emptySeats);

        LocalDateTime timeB = LocalDateTime.of(2025, 1, 1, 13, 40);
        Screening screeningB = new Screening(testMovie, timeB, emptySeats);

        LocalDateTime timeC = LocalDateTime.of(2025, 1, 1, 10, 20);
        Screening screeningC = new Screening(testMovie, timeC, emptySeats);

        assertThat(screeningA.isTimeOverlap(screeningB)).isFalse();
        assertThat(screeningB.isTimeOverlap(screeningA)).isFalse();

        assertThat(screeningA.isTimeOverlap(screeningC)).isFalse();
        assertThat(screeningC.isTimeOverlap(screeningA)).isFalse();
    }

    @Test
    void isTimeOverlapShouldReturnTrueForOverlappingTimes() {
        LocalDateTime timeA = LocalDateTime.of(2025, 1, 1, 12, 0);
        Screening screeningA = new Screening(testMovie, timeA, emptySeats);

        LocalDateTime timeB = LocalDateTime.of(2025, 1, 1, 12, 30);
        Screening screeningB = new Screening(testMovie, timeB, emptySeats);

        LocalDateTime timeC = LocalDateTime.of(2025, 1, 1, 11, 30);
        Screening screeningC = new Screening(testMovie, timeC, emptySeats);
        
        Movie shortMovie = new Movie("Short", 30);
        Screening screeningD = new Screening(shortMovie, LocalDateTime.of(2025, 1, 1, 12, 10), emptySeats);
        
        Movie longMovie = new Movie("Long", 240);
        Screening screeningE = new Screening(longMovie, LocalDateTime.of(2025, 1, 1, 11, 0), emptySeats);

        assertThat(screeningA.isTimeOverlap(screeningB)).isTrue();
        assertThat(screeningA.isTimeOverlap(screeningC)).isTrue();
        assertThat(screeningA.isTimeOverlap(screeningD)).isTrue();
        assertThat(screeningA.isTimeOverlap(screeningE)).isTrue();
    }
}