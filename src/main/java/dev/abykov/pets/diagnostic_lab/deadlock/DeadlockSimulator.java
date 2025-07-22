package dev.abykov.pets.diagnostic_lab.deadlock;

public class DeadlockSimulator {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void run() {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }

                synchronized (lock2) {
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }

                synchronized (lock1) {
                }
            }
        });

        t1.start();
        t2.start();
    }
}
