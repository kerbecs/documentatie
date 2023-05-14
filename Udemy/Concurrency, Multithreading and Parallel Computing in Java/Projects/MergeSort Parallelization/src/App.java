import java.util.Random;

public class App {
    public static void main(String[] args){
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        int[] numbers1 = createArray(10000000);
        int[] numbers2 = new int[numbers1.length];
        for(int i = 0;i<numbers1.length;i++)
            numbers2[i] = numbers1[i];

        ParallelMergeSort parallelMergeSort = new ParallelMergeSort(numbers1);
        long startTime1 = System.currentTimeMillis();
        parallelMergeSort.parallelMergeSort(0,numbers1.length-1,numOfThreads);
         long endTime1 = System.currentTimeMillis();
         System.out.printf("Time taken with parallel: %6d ms",endTime1 - startTime1);

         long startTime2 = System.currentTimeMillis();
         MergeSort mergeSort = new MergeSort(numbers2);
         mergeSort.mergeSort(0,numbers2.length-1);
         long endTime2 = System.currentTimeMillis();
        System.out.printf("\nTime taken with parallel: %6d ms",endTime2 - startTime2);



    }
    private static int[] createArray(int n){
        Random random = new Random();
        int[] a = new int[n];

        for(int i = 0;i<n;++i){
            a[i] = random.nextInt(n);
        }

        return a;

    }

}
