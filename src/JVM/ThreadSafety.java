package JVM;

import java.util.Vector;

public class ThreadSafety {
    private static Vector<Integer> v = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++)
                v.add(i);

            Thread remove = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (v) {
                        for (int i = 0; i < v.size(); i++)
                            v.remove(i);
                    }
                }
            });

            Thread print = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (v) {
                        for (int i = 0; i < v.size(); i++)
                            System.out.println(v.get(i));
                    }
                }
            });

            remove.start();
            print.start();

            while (Thread.activeCount() > 20);
        }
    }
}
