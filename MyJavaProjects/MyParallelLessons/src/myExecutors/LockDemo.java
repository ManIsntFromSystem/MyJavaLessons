package myExecutors;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new LockThread(lock, "A").run();
        new LockThread(lock, "B").run();
    }
}
class Shared{
    static int count = 0;
}

class LockThread implements Runnable{
    String name;
    ReentrantLock lock;

    public LockThread(ReentrantLock lock, String name) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("thread " + name + " locked count");
        Shared.count++;
        lock.lock();
        System.out.println("thread " + name + ": " + Shared.count);
        System.out.println("thread " + name + " waiting");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Thread " + name + " unlocked count");

            lock.unlock();
        }
    }
}
