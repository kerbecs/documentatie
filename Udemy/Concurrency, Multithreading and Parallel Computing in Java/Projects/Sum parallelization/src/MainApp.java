public class MainApp {
    public static void main(String[] args) throws InterruptedException {

     int[] nums = {1,2,3,4,5};
     SequantialSum sequantialSum = new SequantialSum();
     System.out.println(sequantialSum.sum(nums));

     int n = Runtime.getRuntime().availableProcessors();
     ParallelSum parallelSum = new ParallelSum(n);
     System.out.println(parallelSum.sum(nums));

    }

}

