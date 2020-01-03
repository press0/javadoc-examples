package javadoc.blockingqueue;

import java.util.concurrent.BlockingQueue;

class Producer<T> implements Runnable {
    private final BlockingQueue<T> queue;
    private int count;

    Producer(BlockingQueue<T> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public void run() {
        try {
            while (0 < count--) {
                queue.put(produce());
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    T produce() {
        System.out.println("produce " + count);
        return (T) Integer.valueOf(count);
    }

    BlockingQueue<T> getBlockingQueue() {
        return queue;
    }
}