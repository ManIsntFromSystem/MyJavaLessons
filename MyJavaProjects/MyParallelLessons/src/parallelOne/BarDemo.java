package parallelOne;

import javax.crypto.spec.PSource;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarDemo {
    public static void main(String[] args) {
        CyclicBarrier cb =   new CyclicBarrier(3, new BarAction());

        System.out.println("Start threads");

        new MyThread1(cb, "A").run();
        System.out.println("no");
        new MyThread1(cb, "B").run();
        new MyThread1(cb, "C").run();
    }

    static class MyThread1 implements Runnable {
        CyclicBarrier cbar;
        String name;

        public MyThread1(CyclicBarrier c, String n) {
            cbar = c;
            name = n;
            System.out.println("ok");
        }

        @Override
        public void run() {
            System.out.println(name);
            try {
                cbar.await();
                System.out.println("T");
            } catch (BrokenBarrierException | InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    static class BarAction implements Runnable {
        @Override
        public void run() {
            System.out.println("Barrier is over!");
        }
    }
}