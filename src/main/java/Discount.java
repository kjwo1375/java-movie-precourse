
public class Discount {

    public int finalPrice(int originalPrice, int day, int time, int points, String paymentMethod){
        int afterDayDiscount = movieDayDiscount(originalPrice, day);
        int afterTimeDiscount = timeDiscount(afterDayDiscount, time);
        
        int afterPointDiscount = afterTimeDiscount - points; 

        int afterPaymentDiscount = paymentDiscount(afterPointDiscount, paymentMethod);
        return afterPaymentDiscount;
    }

    private int movieDayDiscount(int originalPrice, int day) {
        if (day == 10 || day == 20 || day == 30) return originalPrice - (originalPrice / 10);
        return originalPrice;
    }

    private int timeDiscount(int originalPrice, int time){
        if (time < 11 || time >= 20) return originalPrice - 2000;
        return originalPrice;
    }

    private int paymentDiscount(int originalPrice, String paymentMethod){
        if (paymentMethod.equals("card")) return originalPrice - ( originalPrice / 20 );
        if (paymentMethod.equals( "cash")) return originalPrice - (originalPrice / 50);
        

        return originalPrice;
    }
}