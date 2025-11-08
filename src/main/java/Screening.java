import java.time.LocalDateTime;
import java.util.List;

public class Screening {
    private Movie movie;
    private LocalDateTime startTime;
    private List<Seat> seats;

    public Screening(Movie movie, LocalDateTime startTime, List<Seat> seats){
        this.movie = movie;
        this.startTime = startTime;
        this.seats = seats;
    }

    public Seat reserveSeat(String row, int col){
        Seat targetSeat = findSeat(row, col);

        return checkAndReserve(targetSeat);

    }

    private Seat findSeat(String row, int col){
        return this.seats.stream().filter(seat -> seat.isSameSeat(row, col)).findFirst().orElse(null);
    }

    private Seat checkAndReserve(Seat targetSeat){
        if (targetSeat == null) return null; // Case : No seat
        if (targetSeat.isOccupied()) return null; // Case : Already reserved
        targetSeat.occupy();
        return targetSeat;
    }

    public boolean isTimeOverlap(Screening other){ // check duplication 
        LocalDateTime myEndTime = this.getEndTime();
        LocalDateTime otherEndTime = other.getEndTime();

        boolean aEndsBeforeBStarts = myEndTime.isBefore(other.startTime) || myEndTime.isEqual(other.startTime);
        boolean aStartsAfterBEnds = this.startTime.isAfter(otherEndTime) || this.startTime.isEqual(otherEndTime);
        boolean notOverlap = aEndsBeforeBStarts || aStartsAfterBEnds;

        return !notOverlap;
    }

    public LocalDateTime getEndTime(){
        int runtime = this.movie.getRuntimeInMinutes();

        return this.startTime.plusMinutes(runtime);
    }

}