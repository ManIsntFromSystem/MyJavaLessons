package myExecutors;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleExec {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(5);
        CountDownLatch cdl2 = new CountDownLatch(5);
        CountDownLatch cdlЗ = new CountDownLatch(5);
        CountDownLatch cdl4 = new CountDownLatch(5);

        ExecutorService es = Executors.newFixedThreadPool(2);

        System.out.println("Зaпycк потоков");  //запустить потоки исполнения

        es.execute(new MyThread(cdl, "А"));
        es.execute(new MyThread(cdl2, "В"));
        es.execute(new MyThread(cdlЗ, "С"));
        es.execute(new MyThread(cdl4, "D"));

        try {
            cdl.await();
            cdl2.await();
            cdlЗ.await();
            cdl4.await();
        } catch (InterruptedException ехс) {
            System.out.println(ехс);
        }
        es.shutdown();
        System.out.println("Зaвepшeниe потоков");
    }
}
class MyThread implements Runnable {
     String name;
     CountDownLatch latch;

     public MyThread(CountDownLatch с, String n) {
         latch = с;
         name = n;
     }
     public void run() {
         for (int i = 0; i < 5; i++) {
             System.out.println(name + ": " + i);
             latch.countDown();
         }
     }
}
