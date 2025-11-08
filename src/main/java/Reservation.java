// src/main/java/Reservation.java

import java.util.List;
import java.util.ArrayList;

public class Reservation {

    private List<Screening> screeningsToWatch;
    private List<Seat> selectedSeats;
    private Discount discount;

    public Reservation(Discount discount) {
        this.screeningsToWatch = new ArrayList<>();
        this.selectedSeats = new ArrayList<>();
        this.discount = discount;
    }

    public void addScreening(Screening newScreening) {
        boolean hasOverlap = this.checkOverlap(newScreening);

        if (hasOverlap) return;
        this.screeningsToWatch.add(newScreening);
    }

    public void selectSeat(Screening screening, String row, int col) {
        Seat selectedSeat = screening.reserveSeat(row, col);

        if (selectedSeat == null) return;
        this.selectedSeats.add(selectedSeat);
    }

    public int getFinalPrice(int day, int time, int points, String paymentMethod) {
        int basePrice = calculateBasePrice();
        return this.discount.finalPrice(basePrice, day, time, points, paymentMethod);
    }

    private int calculateBasePrice() {
        return this.selectedSeats.stream().mapToInt(seat -> seat.getOriginalPrice()).sum();
    }

    private boolean checkOverlap(Screening newScreening) {
        return this.screeningsToWatch.stream().anyMatch(existingScreening -> existingScreening.isTimeOverlap(newScreening));
    }
}