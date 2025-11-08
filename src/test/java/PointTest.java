// src/test/java/PointTest.java
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    @Test
    void earnPointShouldAddOnePercentOfPrice() {
        Point point = new Point();

        int returnedPoint = point.earnPoint(10000);

        assertThat(returnedPoint).isEqualTo(100);
        assertThat(point.point).isEqualTo(100);
    }

    @Test
    void earnPointShouldAccumulate() {
        Point point = new Point();

        point.earnPoint(10000);
        int returnedPoint = point.earnPoint(5000);

        assertThat(returnedPoint).isEqualTo(150);
        assertThat(point.point).isEqualTo(150);
    }

    @Test
    void earnPointShouldHandleIntegerDivision() {
        Point point = new Point();

        int returnedPoint = point.earnPoint(99);

        assertThat(returnedPoint).isEqualTo(0);
        assertThat(point.point).isEqualTo(0);
        
        int returnedPoint2 = point.earnPoint(199);
        
        assertThat(returnedPoint2).isEqualTo(1);
        assertThat(point.point).isEqualTo(1);
    }
}