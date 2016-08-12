import java.util.Scanner;

public class BasicMath {
    private int sum, difference, product, quotient, remainder;

    public BasicMath(int sum, int difference, int product, int quotient, int remainder) {
        this.sum = sum;
        this.difference = difference;
        this.product = product;
        this.quotient = quotient;
        this.remainder = remainder;
    }

    public int getSum() {return this.sum; }

    public int getDiffernce() {return this.difference; }

    public int getProduct() {return this.product; }

    public int getQuotient() {return this.quotient; }

    public int getRemainder() {return this.remainder; }

    private static BasicMath Calculcate(short a, short b) {
        // Compute unsigned sum, difference, product, quotient, remainder without converting input to int
        int sum = Short.toUnsignedInt(a) + Short.toUnsignedInt(b);
        int diff = Short.toUnsignedInt(a) - Short.toUnsignedInt(b);
        int product = Short.toUnsignedInt(a) * Short.toUnsignedInt(b);
        int quotient = Short.toUnsignedInt(a) / Short.toUnsignedInt(b);
        int remainder = Short.toUnsignedInt(a) % Short.toUnsignedInt(b);

        return new BasicMath(sum, diff, product, quotient, remainder);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        short a = (short) scanner.nextInt();
        short b = (short) scanner.nextInt();

        BasicMath answer = Calculcate(a,b);

        System.out.println("Unsigned sum is : " + answer.getSum());
        System.out.println("Unsigned difference is : " + answer.getDiffernce());
        System.out.println("Unsigned product is : " + answer.getProduct());
        System.out.println("Unsigned quotient is : " + answer.getQuotient());
        System.out.println("Unsigned remainder is : " + answer.getRemainder());

    }
}
