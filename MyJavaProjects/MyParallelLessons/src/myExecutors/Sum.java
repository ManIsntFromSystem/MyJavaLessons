package myExecutors;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Sum extends RecursiveTask {
    //processed arrays
    double data[];

    //define a part processed data
    int start, end;

    final int seqThreshold = 500;

    public Sum(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        double sum = 0;

        if ((end - start) < seqThreshold) {
            for (int i = start; i < end; i++) {
                sum += (data[i]);
            }
        } else {
            int middle = (start + end) / 2;

            Sum subTaskA = new Sum(data, start, middle);
            Sum subTaskB = new Sum(data, middle, end);

            subTaskA.fork();
            subTaskB.fork();

            double ar1 = (double) subTaskA.join();
            //double ar2 = (double) subTaskA.invoke();
            //double ar2 = (double) subTaskA.join();
            //sum = subTaskA.join() + subTaskA.invoke();
            sum = ar1 + subTaskB.compute();
            //sum = ar1 + ar2;
        }
        return sum;
    }
}
class RecurTaskTaskDemo {
    public static void main(String[] args) {

        ForkJoinPool fjp = new ForkJoinPool();

        double nums[] = new double[5000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double) (((i % 2) == 0) ? i : -i);
        }
        Sum task = new Sum(nums, 0, nums.length);

        double summation = (double) fjp.invoke(task);
        System.out.println("Summation: " + summation);
    }
}
