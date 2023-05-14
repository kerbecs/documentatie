import java.util.concurrent.ForkJoinPool;

public class MyApp {
    public static void main(String[] args){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long count1 = 0;
        long count2 = 0;
        int nr = 40;

        count1 = System.currentTimeMillis();
        Fibonacci f = new Fibonacci(nr);


        System.out.printf("The %dth number is: %d",nr,f.invoke());
        f.join();
        count1 = System.currentTimeMillis() - count1;
        System.out.println();

        count2 = System.currentTimeMillis();
        System.out.printf("The %dth number is: %d",nr,new OldFib().count(nr));
        count2 = System.currentTimeMillis() - count2;

        System.out.println("\nParallel method: "+count1+" ms");
        System.out.println("Recursive method: "+count2+" ms");

    }
}

class OldFib{
    public int count(int nr){
        if(nr <= 1)
            return nr;
        return count(nr-1) + count(nr - 2);
    }
}
