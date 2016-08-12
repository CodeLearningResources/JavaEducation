import java.util.ArrayDeque;

/**
 * Created by mikim on 2016-07-26.
 */

class PrintDemo {
    private String name;

    public PrintDemo(String name) {
        this.name = name;
    }

    public void print() {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(this.name + " is Up. Counter : " + i);
            }
        } catch (Exception e) {
            System.out.println("Thread interrupted.");
        }
    }
}
public class Run implements Runnable {
    private final String threadName;
    PrintDemo pd;

    public Run(String name, PrintDemo pd) {
        this.threadName = name;
        this.pd = pd;
    }

    @Override
    public void run() {
        synchronized (pd) {
            pd.print();
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public static void runTogether(Runnable... tasks) {
        // Chapter 3 #9. Run each task in a separate thread and then return
        ArrayDeque<Thread> threads = new ArrayDeque<>();

        for (Runnable task: tasks) {
            Thread t1 = new Thread(task);
            threads.add(t1);
            t1.start();
        }


        try {
            while (!threads.isEmpty()) {
                threads.poll().join();
            }
        } catch (Exception  e) {
            System.out.println("Interrupted!");
        }
    }

    public static void  runInOrder(Runnable... tasks) {
        // Chapter 3 #9. Run all methods in the current thread and return when the last one has completed.
        for (Runnable task: tasks) {
            task.run();
        }
    }

    public static void  main(String[] args) {
        PrintDemo pd = new PrintDemo("Hello");
        Runnable[] tasks = new Runnable[3];
        for (int i = 0; i < 3; i++) {
            tasks[i] = new Run("Machine " + i, pd);
        }

        Run.runTogether(tasks);
//        Run.runInOrder(tasks);
    }
}