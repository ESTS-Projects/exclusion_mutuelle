class Counter3 {
    public int count;
    private static int T = 0;
    private static boolean[] C = { false, false };

    public Counter3() {
        count = 0;
    }

    public synchronized void increment() {
        int id = T;

        while (true) {
            C[id] = true;

            while (C[1 - id]) {
            }

            count++;
            C[id] = false;
            break;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(count);
    }
}

public class Exercice3 {
    public static void main(String[] args) throws Exception {
        Counter3 counter = new Counter3();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000000; i++) {
                    counter.increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000000; i++) {
                    counter.increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter : " + counter.count);
    }
}
