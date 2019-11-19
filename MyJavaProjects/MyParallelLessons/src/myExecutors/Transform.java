package myExecutors;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class Transform extends RecursiveAction {

    //processed arrays
    double data[];

    //define a part processed data
    int start, end;

    int seqThreshold;

    public Transform(double[] data, int start, int end, int seqThreshold) {
        this.data = data;
        this.start = start;
        this.end = end;
        this.seqThreshold = seqThreshold;
    }

    @Override
    protected void compute() {
        if( (end - start) < seqThreshold) {
            for (int i = start; i < end; i++) {
                if ((data[i] % 2) == 0)
                    data[i] = Math.sqrt(data[i]);
                else
                    data[i] = Math.cbrt(data[i]);
            }
        } else {
        int middle = (start + end) / 2;

        invokeAll(new Transform(data, start, middle,seqThreshold),
                new Transform(data, middle, end,seqThreshold));
        }
    }
}
    class FJExperiment {
        public static void main(String args[]) {

            int pLevel;
            int threshold;

            if (args.length != 2) {
                System.out.println("Used: " +
                        "FJExperiment threshold parallel");
                return;
            }

            pLevel = Integer.parseInt(args[0]);
            threshold = Integer.parseInt(args[1]); // long endT;

            long beginT, endT;

            ForkJoinPool fjp = new ForkJoinPool(pLevel);

            double[] nums = new double[1000000];

            for(int i = 0; i < nums.length; i++)
                nums[i] = (double) i;

            Transform task = new Transform(nums, 0, nums.length, threshold);

            beginT = System.nanoTime();

            fjp.invoke(task);

            endT = System.nanoTime();

            System.out.println("Level parallel: "+ pLevel);
            System.out.println("Sequential processing threshold: " + threshold);
            System.out.println("Elapsed time: " + (endT - beginT) + " ns");
            System.out.println();
            System.out.println(fjp.getParallelism());
            System.out.println(ForkJoinPool.getCommonPoolParallelism());
            System.out.println(Runtime.getRuntime().availableProcessors());
        }
    }

