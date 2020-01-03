package javadoc.semaphore;

import java.util.concurrent.Semaphore;

public class LoginSemaphore {

    private Semaphore semaphore;

    public LoginSemaphore(int slotLimit) {
        semaphore = new Semaphore(slotLimit);
    }

    boolean tryLogin() {
        return semaphore.tryAcquire();
    }

    void logout() {
        semaphore.release();
    }

    int availableSlots() {
        return semaphore.availablePermits();
    }

}
