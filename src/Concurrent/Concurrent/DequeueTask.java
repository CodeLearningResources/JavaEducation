package Concurrent.Concurrent;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mikim on 2016-09-12.
 */
public class DequeueTask implements Runnable {

    static final String dummy = "dummy.txt";
    /*
    Ch 10.10. Use a blocking queue for processing files in a dir. One thread walks the file tree and
    inserts the files into a queue. Several threads remove the files and search each one for a given keyword,
    printing out any matches. When the producer is done, put dummy file into the queue.
    */

    private BlockingQueue<String> myBlockingQueue;
    private String keyword;
    private CountDownLatch latch;

    public DequeueTask(String keyword, BlockingQueue<String> myBlockingQueue, CountDownLatch latch) {
        this.myBlockingQueue = myBlockingQueue;
        this.keyword = keyword;
        this.latch = latch;
    }

    @Override
    public void run() {
        String path;
        try {
            while (!(path = myBlockingQueue.take()).equals(dummy)) {
                if (path.contains(keyword)) {
                    System.out.println(path);
                }
            }
            myBlockingQueue.add(dummy);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        latch.countDown();
    }
}
