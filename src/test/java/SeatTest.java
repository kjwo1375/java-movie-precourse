
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SeatTest {

    @Test
    void seatPriceShouldBeCorrectByRank() {

        Seat s_seat = new Seat("A", 1, "S");
        assertThat(s_seat.originalPrice).isEqualTo(18000);

        Seat a_seat = new Seat("B", 2, "A");
        assertThat(a_seat.originalPrice).isEqualTo(15000);

        Seat b_seat = new Seat("C", 3, "B");
        assertThat(b_seat.originalPrice).isEqualTo(12000);

        Seat other_seat = new Seat("D", 4, "Z");
        assertThat(other_seat.originalPrice).isEqualTo(0);
    }

    @Test
    void isSameSeatShouldWorkCorrectly() {
        Seat seat = new Seat("A", 1, "S");

        assertThat(seat.isSameSeat("A", 1)).isTrue();

        assertThat(seat.isSameSeat("B", 1)).isFalse();
   
        assertThat(seat.isSameSeat("A", 2)).isFalse();

        assertThat(seat.isSameSeat("C", 3)).isFalse();
    }

    @Test
    void occupyStatusShouldChangeCorrectly() {
        Seat seat = new Seat("B", 5, "A");

        assertThat(seat.isOccupied()).isFalse();

        seat.occupy();

        assertThat(seat.isOccupied()).isTrue();
    }
}