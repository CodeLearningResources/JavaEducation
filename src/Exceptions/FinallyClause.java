package Exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mikim on 2016-07-29.
 */
public class FinallyClause {



    private static MyReentrantLock createMyReentrantLock(ReentrantLock lock) {
        /*
        Instructions: Use a ReentrantLock in a try-with-resources.
        Call 'lock' & return AutoCloseable whose close method calls 'unlock' and throws no exception
         */
        lock.lock();
        return new MyReentrantLock(lock);
    }

    private static void action() {
        ReentrantLock lock = new ReentrantLock();

        try (MyReentrantLock autoLock = createMyReentrantLock(lock)) {
            doSomething();
        }
    }

    private static void doSomething() { }

    private static void CatchingInFinally() {
        // Exception Exercise Ch.5 #6 (Catching exception in finally clause)
        BufferedReader in = null;
        try {
            Path path = Paths.get("C:/Users/mikim");
            in = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.err.println("Caught IOException: " + ex.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex1) {
                System.err.println("Caught IOException while close BufferedReader in: " + ex1.getMessage());
            }
        }
    }

    private static void NestedTryCatch() {
        // Exception Exercise Ch.5 #6 (try/catch statement containing a try/finally statement)
        try {
            Path path = Paths.get("C:/Users/mikim");
            BufferedReader in = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            try {
                in.close();
            } finally {
                System.out.println("Able to handle in.close()");
            }
        } catch (IOException ex) {
            System.err.println("Caught IOException while reading BufferedReader in: " + ex.getMessage());
        }
    }

    private static void TryWithResouces() {
        // Exception Exercise Ch.5 #6 (try-with-resources statement with a catch clause)
        Path path = Paths.get("C:/Users/mikim");
        try (BufferedReader in = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            // Read from in

        } catch (IOException ex) {
            System.err.println("Caught IOException in BufferedReader in: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        CatchingInFinally();
        NestedTryCatch();
        TryWithResouces();

        ReentrantLock lock = new ReentrantLock();
        createMyReentrantLock(lock);

    }

}
