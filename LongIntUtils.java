public class LongIntUtils {
    public static int overflow(int t) {
        return t / 100000000;
    }

    public static int underflow(int t) {
        return t % 100000000;
    }

    public static int upperHalf(int t) {
        return (t / 10000) % 10000;
    }

    public static int lowerHalf(int t) {
        return t % 10000;
    }

    public static int digits(int t) {
        String dig = "" + t;
        return dig.length();
    }

    public static void main(String args[]) {
        System.out.println("Overflow: " + LongIntUtils.overflow(999123456));
        System.out.println("Underflow: " + LongIntUtils.underflow(999123456));
        System.out.println("Lower Half: " + LongIntUtils.lowerHalf(123456));
        System.out.println("Upper Half: " + LongIntUtils.upperHalf(123456));
        System.out.println("Digits: " + LongIntUtils.digits(123456));
    }
}
