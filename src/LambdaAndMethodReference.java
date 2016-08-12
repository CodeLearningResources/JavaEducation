import java.io.File;
import java.io.FileFilter;
/**
 * Created by mikim on 2016-07-22.
 */
public class LambdaAndMethodReference implements Runnable {
    private final String name;

    public LambdaAndMethodReference(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " is Up.");
    }

    public static File[] returnSubdirViaAnonymousClass(File inputDir) {
        // Chapter 3 #10 via anonymous class
        return inputDir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
    }

    public static File[] returnSubdirViahLambda(File inputDir) {
        // Chapter 3 #10 via lambda expression
        return inputDir.listFiles( (pathname) -> inputDir.isDirectory() );
    }

    public static File[] returnSubdirViaMethodReference(File inputDir) {
        // Chapter 3 #10 via method reference
        return inputDir.listFiles(File::isDirectory);
    }

    public static Runnable returnRunnable(Runnable[] runnables) {
        // Chapter 3 #13. Return a Runnable whose run method executes them in order
        return () -> {
            for (Runnable item: runnables) {
                item.run();
            }
        };
    }

    public static void main(String[] args) {

    }
}
