package Exceptions;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mikim on 2016-07-29.
 */
public class MyReentrantLock implements AutoCloseable {

    private final ReentrantLock lock;

    public MyReentrantLock(ReentrantLock lock) {
        this.lock = lock;
    }

    public void close() {
        this.lock.unlock();
    }
}
