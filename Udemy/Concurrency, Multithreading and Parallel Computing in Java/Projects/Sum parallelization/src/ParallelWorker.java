public class ParallelWorker extends Thread {
    private int[] nums;
    private int low;
    private int high;
    private int partialSum;

    public ParallelWorker(int[] nums,int low, int high){
        this.nums = nums;
        this.low = low;
        this.high = Math.min(high,nums.length);
    }
    @Override
    public void run() {
        for(int i = low;i<high;i++)
            partialSum+=nums[i];
    }

    public int getPartialSum() {
        return partialSum;
    }
}
