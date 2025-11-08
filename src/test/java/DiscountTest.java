// src/test/java/DiscountTest.java
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DiscountTest {


    Discount discount = new Discount();

    @Test
    void allDiscountsAndPointsTest() {

        int finalPrice = discount.finalPrice(20000, 10, 10, 1000, "card");

        assertThat(finalPrice).isEqualTo(14250);
    }

    @Test
    void noDiscountsTest() {


        int finalPrice = discount.finalPrice(20000, 11, 12, 0, "bankTransfer");


        assertThat(finalPrice).isEqualTo(20000);
    }

    @Test
    void onlyCashDiscountTest() {


        int finalPrice = discount.finalPrice(20000, 11, 12, 0, "cash");


        assertThat(finalPrice).isEqualTo(19600);
    }

    @Test
    void lateTimeDiscountTest() {

        
        int finalPrice = discount.finalPrice(20000, 11, 20, 0, "other");

        assertThat(finalPrice).isEqualTo(18000);
    }
}