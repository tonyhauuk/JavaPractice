package JVM;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DeadLock {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        for (int i = 0; i < 100; i++) {
            new Thread(new SynAddRunable(1, 2)).start();
            new Thread(new SynAddRunable(3, 4)).start();
        }
    }

    static class SynAddRunable implements Runnable {
        int a, b;
        public SynAddRunable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(this.a + this.b);
                }
            }
        }
    }
}
