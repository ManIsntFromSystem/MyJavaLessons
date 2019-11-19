package parallelOne;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser {

    int numPhases;

    public MyPhaser(int parties, int phaseCount) {
        super(parties);
        numPhases = phaseCount - 1;
    }
    protected boolean onAdvance(int p, int regParties) {

        System.out.println("Phase " + p + " finished");

        if (p == numPhases || regParties == 0)
            return true;
        return false;
    }
}

class PhaserDemo2 {
    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser(1, 4);

        System.out.println("Start threads\n");

        new MyThread3(phaser, "A").run();
        new MyThread3(phaser, "B").run();
        new MyThread3(phaser, "C").run();

        while(!phaser.isTerminated()) {
            phaser.arriveAndAwaitAdvance();
        }
        System.out.println("Phase's synchronized finished");
    }
}
class MyThread3 implements Runnable{
    Phaser psr;
    String name;

    public MyThread3(Phaser psr, String name) {
        this.psr = psr;
        this.name = name;
        psr.register();
    }

    @Override
    public void run() {
        while (!psr.isTerminated()){
            System.out.println("Thread " + name + " start phase " +
                    psr.getPhase());

            psr.arriveAndAwaitAdvance();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}