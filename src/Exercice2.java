class Counter2 {
    public int count;
    private static int T = 0;

    public Counter2() {
        count = 0;
    }

    public synchronized void increment() {
        int id = T;

        while (true) {
            while (T != id) {
            }

            count++;
            T = 0;
            break;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(count);
    }
}

public class Exercice2 {
    public static void main(String[] args) throws Exception {
        Counter2 counter = new Counter2();

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
