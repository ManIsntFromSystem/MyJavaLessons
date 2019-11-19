package myExecutors;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Integer> f;
        Future<Double> f2;
        Future<Integer> f3;

        System.out.println("Start");

        f = es.submit(new Sum(10));
        f2 = es.submit(new Hypot(3, 4));
        f3 = es.submit(new Factorial(5));

        try {
            System.out.println(f.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
        } catch (InterruptedException | ExecutionException exc) {
            System.out.println(exc);
        }
        es.shutdown();
    }
    private static class Sum implements Callable<Integer> {
    int stop;
    Sum(int v) {
        stop = v;
    }
    public Integer call() {
        int sum = 0;
        for (int i = 1; i <= stop; i++) {
            sum += i;
        }
        return sum;
    }
}
    private static class Hypot implements Callable<Double> {
        double sidel, side2;

        Hypot(double sl, double s2) {
            sidel = sl;
            side2 = s2;
        }

        public Double call() {
            return Math.sqrt((sidel * sidel) + (side2 * side2));
        }
    }
    private static class Factorial implements Callable<Integer> {
        int stop;

        Factorial(int v) {
            stop = v;
        }

        public Integer call() {
            int fact = 1;
            for (int i = 2; i < stop; i++) {
                fact *= i;
            }
            return fact;
        }
    }
}
