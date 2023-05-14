import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class MyApp {
    public static void main(String[] args){
    long[] nums = create(100000000);

    ForkJoinPool forkJoinPool = new ForkJoinPool();
    SequentialFinding s = new SequentialFinding();
    long start = System.currentTimeMillis();

    System.out.println("Max: "+s.max(nums));
    System.out.println("Time with sequential: "+ (System.currentTimeMillis() - start) +" ms");

    start = System.currentTimeMillis();
    ParallelFinding parallelFinding = new ParallelFinding(nums,0,nums.length);
        System.out.println("Max: "+forkJoinPool.invoke(parallelFinding));
        System.out.println("Time with parallel: "+ (System.currentTimeMillis() - start) +" ms");

    }
    private static long[] create(int n){
        Random random = new Random();
        long[] array = new long[n];

        for(int i = 0;i<n;i++){
            array[i] = random.nextInt(1000);
        }

        return array;
    }
}


