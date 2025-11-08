public class Movie {
    String MovieName;
    int runtimeInMinutes;

    public Movie(String name, int runtimeInMinutes){
        this.MovieName = name;
        this.runtimeInMinutes = runtimeInMinutes;

    }

    public int getRuntimeInMinutes(){
        return this.runtimeInMinutes;
    }

}
