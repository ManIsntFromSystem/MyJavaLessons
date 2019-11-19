package parallelOne;

import java.util.concurrent.*;

public class PhaserDemo {
    public static void main(String[] args) {
        Phaser phsr = new Phaser(1);
        int curPhase;

        System.out.println("Start threads");

        new MyThread2(phsr, "A").run();
        new MyThread2(phsr, "B").run();
        new MyThread2(phsr, "C").run();

        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Phase "+ curPhase + "finished");

        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Phase "+ curPhase + "finished");

        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Phase "+ curPhase + "finished");

        phsr.arriveAndDeregister();

        if(phsr.isTerminated()){
            System.out.println("Phase's synchronized finished");
        }

    }

    private static class MyThread2 implements Runnable{
        Phaser phaser;
        String name;
        public MyThread2(Phaser phsr, String a) {
            phaser = phsr;
            name = a;
            phaser.register();
        }

        @Override
        public void run() {
            System.out.println("Thread " + name + " starts first phase");
            phaser.arriveAndAwaitAdvance();

            try {
                Thread.sleep(10);
            }catch (InterruptedException e) {
                System.out.println(e);
            }

            System.out.println("Thread " + name + "starts second phase");
            phaser.arriveAndAwaitAdvance();

            try {
                Thread.sleep(10);
            }catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println("Thread " + name + "starts first phase");
            phaser.arriveAndDeregister();
        }
    }
}
