package javadoc.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Consumer<T> implements Runnable {
    static AtomicInteger atomicInteger = new AtomicInteger();
    private final BlockingQueue<T> queue;

    Consumer(BlockingQueue<T> q) {
        queue = q;
    }

    public void run() {
        try {
            while (true) {
                T t = queue.poll(1, TimeUnit.SECONDS);
                if (t == null) {
                    break;
                }
                atomicInteger.getAndIncrement();
                consume(t);
            }
        } catch (InterruptedException ex) {
            System.out.println("consume " + ex);
        }
    }

    void consume(T t) {
        System.out.println("consume " + t);
    }
}