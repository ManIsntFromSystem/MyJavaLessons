package myExecutors;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class SqrtTransform extends RecursiveAction {

    final int seqThreshold = 1000;

    //processed arrays
    double data[];

    //define a part processed data
    int start, end;

    public SqrtTransform(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    //this method performs parallel computing
    @Override
    protected void compute() {
        if((end - start) < seqThreshold) {
            for (int i = start; i < end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        }
        else {
            //find out a center
            int middle = (start + end) / 2;
            //run on new tasks
            //to use shared parts of data
            invokeAll(new SqrtTransform(data, start, middle),
                    new SqrtTransform(data, middle, start));
        }
    }
}
//Demonstrate parallel execution
class ForkJoinDemo {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();

        //here called common pool
        ForkJoinPool fjp1 = ForkJoinPool.commonPool();

        double[] nums = new double[1000];

        //to assign some value
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double)i;
        }
        System.out.println("Part of the original sequence");
        for (int i = 0; i < 10; i++) {
            System.out.println(nums[i] + " \n");
        }
        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);

        //run main task of ForkJoin type run on
        //fjp.invoke(task);
        //its better such way
        task.invoke();

        System.out.println("Part of the transformation " +
                "sequence (with precision up to " +
                "four characters after decimal point): ");
        for (int i = 0; i < 10; i++) {
            System.out.format("%.4f", nums[i]);
            System.out.println();
        }
    }
}
