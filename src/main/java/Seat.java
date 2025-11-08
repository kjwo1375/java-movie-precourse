public class Seat {
    String row;
    int col;
    boolean occupied = false;
    String rank;
    int originalPrice;

    public Seat(String row, int col,String rank) {
        this.row = row;
        this.col = col;
        this.rank = rank;
        this.originalPrice = calculatePriceByRank(rank);
    }

    private int calculatePriceByRank(String rank) {
        if (rank.equals("S")) return 18000;
        
        if (rank.equals("A")) return 15000;
        
        if (rank.equals("B")) return 12000;
        
        return 0;
    }
    public boolean isSameSeat(String UserRow, int UserCol){
        boolean sameRow = this.row.equals(UserRow);
        boolean sameCol = (this.col == UserCol);

        return sameRow && sameCol;
    }

    public void occupy(){
        this.occupied = true;
    }
    public boolean isOccupied(){
        return this.occupied;
    }
    public int getOriginalPrice(){
        return this.originalPrice;
    }

}