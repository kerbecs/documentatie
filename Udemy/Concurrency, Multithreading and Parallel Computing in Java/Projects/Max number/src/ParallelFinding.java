import java.util.concurrent.RecursiveTask;

public class ParallelFinding extends RecursiveTask<Long> {

    private long[] nums;
    private int lowIndex;
    private int highIndex;

    public ParallelFinding(long[] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected Long compute() {
        if(highIndex - lowIndex <3000){
            return sequentialMaxFinding();
        }
        else{
            int middle = (highIndex+lowIndex)/2;
            ParallelFinding parallelFinding1 = new ParallelFinding(nums,lowIndex,middle);
            ParallelFinding parallelFinding2 = new ParallelFinding(nums,middle+1,highIndex);
            parallelFinding1.fork();
            parallelFinding2.fork();


            return Math.max(parallelFinding1.join(),parallelFinding2.join());
        }
    }
    private Long sequentialMaxFinding(){
        long max = nums[lowIndex];

        for(int i = lowIndex+1;i<highIndex;i++)
            if(nums[i] > max)
                max = nums[i];

        return max;
    }
}
