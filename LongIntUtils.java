
// Called using LongIntUtils.method(...)

public class LongIntUtils {
    
    public static int overflow(int t) {

        /*divide integer by 100000000 to get 9th digit */

        int over = t / 100000000;
        
        return over;
    }
    
    public static int underflow(int t) {

        int under = t % 100000000;

        return under;
    }
    
    public static int upperHalf(int t) {
        
        int upper = (t / 10000) % 10000;

        return upper;
    }
    
    public static int lowerHalf(int t) {

        int lower = t % 10000;

        return lower;
    }
    
    public static int digits(int t) {

        String dig = "" + t;
        int length = dig.length();

        return length;
    }
    public static void main(String args[]){

        System.out.println("Overflow: " + LongIntUtils.overflow(123456));
        System.out.println("Underflow: " + LongIntUtils.underflow(123456));
        System.out.println("Lower Half: " + LongIntUtils.lowerHalf(123456));
        System.out.println("Upper Half: " + LongIntUtils.upperHalf(123456));
        System.out.println("Digits: " + LongIntUtils.digits(123456));

    }
}
