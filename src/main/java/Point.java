public class Point {
    int point;

    public int earnPoint(int originalPrice){
        point = point + ( originalPrice / 100 );
        return point;
    }

}
