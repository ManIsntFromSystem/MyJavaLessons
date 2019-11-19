package parallelOne;

import java.util.concurrent.Semaphore;

public class SemDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        new IncThread(sem, "A").run();
        new DecThread(sem, "B").run();
    }
}
    class Shared {
        static int count = 0;
    }

    class IncThread implements Runnable{
        Semaphore sem;
        String name;

        public IncThread(Semaphore s, String n) {
            sem = s;
            name = n;
        }

        @Override
        public void run() {
            System.out.println("Star thread " + name);
            try {
                System.out.println("Thread " + name);
                sem.acquire();
                System.out.println("thread" + " - receive permission");
                // and now to get permission to public resource
                for (int i = 0; i < 5; i++) {
                    Shared.count++;
                    System.out.println(name + ": " + Shared.count);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e){
                System.out.println(e);
            }
            System.out.println("Thread " + name + " - exempt permission");
            sem.release();
        }
    }

class DecThread implements Runnable{
    Semaphore sem;
    String name;

    public DecThread(Semaphore s, String n) {
        sem = s;
        name = n;
    }

    @Override
    public void run() {
        System.out.println("Star thread " + name);
        try {
            System.out.println("Thread " + name);
            sem.acquire();
            System.out.println("thread" + " - receive permission");
            // and now to get permission to public resource
            for (int i = 0; i < 5; i++) {
                Shared.count--;
                System.out.println(name + ": " + Shared.count);
                Thread.sleep(10);
            }
        } catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Thread " + name + " - exempt permission");
        sem.release();
    }
}