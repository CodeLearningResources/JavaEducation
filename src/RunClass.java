/**
 * Created by mikim on 2016-07-21.
 */
public class RunClass implements Runnable{

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Print : " + i);
        }
    }


    public static void main(String args[]) {
    }
}
