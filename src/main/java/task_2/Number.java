package task_2;

import java.util.concurrent.Semaphore;

public abstract class Number implements Runnable {

    volatile static int number = 0;
    private final Semaphore threadCanTest;
    private final Semaphore canManagerThreadRun;

    protected Number(Semaphore threadCanTest, Semaphore canManagerThreadRun) {
        this.threadCanTest = threadCanTest;
        this.canManagerThreadRun = canManagerThreadRun;
    }

    @Override
    public void run() {
        while (number < Main.MAX_NUM) {
            try {
                // System.err.println("waiting from thread " + Thread.currentThread().getName()  );
                threadCanTest.acquire();
                // System.err.println("running from thread " + Thread.currentThread().getName() + " for value=" + number);
                testAndPrint(number);

                canManagerThreadRun.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public abstract void testAndPrint(int number) throws InterruptedException;


}


