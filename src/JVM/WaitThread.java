package JVM;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WaitThread {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThread(obj);
    }

    public static void createBusyThread() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true);
            }
        }, "Test Busy Thread");
        t.start();
    }

    public static void createLockThread(final Object lock) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Test Lock Thread");
        t.start();
    }
}
