import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {
    private final int nth;

    public Fibonacci(int nth) {
        this.nth = nth;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if(nth > 1){
          Fibonacci f1 = new Fibonacci(nth-1);
          Fibonacci f2 = new Fibonacci(nth-2);

          f1.fork();
          f2.fork();
          sum = f1.join() + f2.join();
        }
        else if(nth == 1){
           return 1;
        }
        else if(nth==0){
            return 0;
        }

        return sum;
    }
}



/*
0
1
1
2
3
5
8
13
21
34
55
89
 */
