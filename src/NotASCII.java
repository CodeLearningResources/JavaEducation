import java.util.Scanner;

/**
 * Created by mikim on 2016-07-18.
 */
public class NotASCII {

    private static void printNonASCIIWithUnicode(String line) {
        // Read a String input and print all characters that are not ASCII, together with their Unicode values
        int len = line.codePointCount(0, line.length());
        boolean flag = false;
        for (int i = 0; i < len; i++ ) {
            if (String.valueOf(line.charAt(i)).matches("[^\\p{ASCII}]")) {
                System.out.println("Non ASCII character is : " + line.charAt(i) + ". Its corresponding Unicode is : " +
                        line.codePointAt(line.offsetByCodePoints(0, i)));
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("All characters from input are ASCII.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        printNonASCIIWithUnicode(line);
    }
}
