package javadoc.countdownlatch;

import java.util.concurrent.CountDownLatch;

class Worker implements Runnable {
    private final int id;
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    Worker(int id, CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.id = id;
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {
        } // return;
    }

    void doWork() {
        System.out.println("work " +id);
    }
}
