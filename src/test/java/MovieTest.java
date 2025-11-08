
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MovieTest {

    @Test
    void movieShouldHaveCorrectInfo() {

        Movie movie1 = new Movie("Avengers", 142);


        assertThat(movie1.MovieName).isEqualTo("Avengers");
        assertThat(movie1.runtimeInMinutes).isEqualTo(142);
    }

    @Test
    void anotherMovieTest() {
        Movie movie2 = new Movie("Inception", 148);
        assertThat(movie2.MovieName).isEqualTo("Inception");
    }
}