class Counter4 {
    public int count;
    private static int T = 0;
    private static boolean[] C = { false, false };

    public Counter4() {
        count = 0;
    }

    public synchronized void increment() {
        int id = T;

        while (true) {
            C[id] = true;
            T = id;

            while (C[1-id] && T == id) {
            }

            count++;
            T = 0;
            C[id] = false;
            break;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(count);
    }
}

public class Exercice4 {
    public static void main(String[] args) throws Exception {
        Counter4 counter = new Counter4();

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
