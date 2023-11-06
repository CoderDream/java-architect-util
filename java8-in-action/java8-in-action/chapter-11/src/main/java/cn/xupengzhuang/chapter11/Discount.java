package cn.xupengzhuang.chapter11;

public class Discount {
    public enum Code{
        NONE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote){
        Double applyPrice = apply(quote.getPrice(), quote.getDiscountCode());
        return quote.getShopName() + " price is " + applyPrice;

    }

    private static Double apply(Double price,Code code){
        delay();
        return price * ((100 - code.percentage) / 100);
    }

    public static void delay(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
