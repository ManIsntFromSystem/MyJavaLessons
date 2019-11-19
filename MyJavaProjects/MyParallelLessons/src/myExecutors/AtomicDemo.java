package myExecutors;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
        new AtomThread("A").run();
        new AtomThread("B").run();
        new AtomThread("C").run();
    }
    private static class Shared {
        static AtomicInteger ai = new AtomicInteger(0);
    }
    private static class AtomThread implements Runnable{
        String name;

        public AtomThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Start thread " + name);

            for (int i = 1; i <= 3 ; i++) {
                System.out.println("Thread " + name + " received: " +
                        Shared.ai.getAndSet(i));
            }
        }
    }
}
