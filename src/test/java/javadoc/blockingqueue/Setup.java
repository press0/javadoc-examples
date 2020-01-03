package javadoc.blockingqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.jupiter.api.Test;

public class Setup {

    @Test
    public void test() throws InterruptedException {
        BlockingQueue<String> q = new ArrayBlockingQueue(3);
        Producer<String> p = new Producer(q, 5);
        Consumer<String> c1 = new Consumer(q);
        Consumer<String> c2 = new Consumer(q);
        new Thread(p).start();

        Thread.sleep(100);
        assertEquals(p.getBlockingQueue().size(), 3);

        new Thread(c1).start();
        new Thread(c2).start();

        Thread.sleep(100);
        assertEquals(5, Consumer.atomicInteger.get());
        assertEquals(q.size(), 0);
    }
}