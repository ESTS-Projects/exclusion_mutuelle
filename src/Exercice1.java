class Counter {
    public int count;
    public static boolean M = false;

    public Counter() {
        count = 0;
    }

    public synchronized void increment() {
        while (M) {
        }

        M = true;
        count++;
        M = false;
    }

    @Override
    public String toString() {
        return Integer.toString(count);
    }
}

public class Exercice1 {
    public static void main(String[] args) throws Exception {
        Counter counter = new Counter();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
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
