package Concurrent.Concurrent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created by mikim on 2016-09-12.
 */
public class EnqueueTask implements Runnable {
    /*
    Ch 10.10. Use a blocking queue for processing files in a dir. One thread walks the file tree and
    inserts the files into a queue. Several threads remove the files and search each one for a given keyword,
    printing out any matches. When the producer is done, put dummy file into the queue.
    */

    private BlockingQueue<String> myBlockingQueue;
    private File file;
    private CountDownLatch latch;

    public EnqueueTask(File file, BlockingQueue<String> myBlockingQueue, CountDownLatch latch) {
        this.myBlockingQueue = myBlockingQueue;
        this.file = file;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Stream<String> paths = Files.walk(file.toPath()).map((path) -> path.getFileName().toString());
            paths.forEach(myBlockingQueue::add);
            myBlockingQueue.add(DequeueTask.dummy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        latch.countDown();
    }
}
