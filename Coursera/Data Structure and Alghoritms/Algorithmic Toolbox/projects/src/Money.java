import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Money {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        int number = in.nextInt();
        int[] coins = {10,5,1};
        int total = 0;

        while(number>0){
            for(int i = 0;i<3;i++){
                if(number-coins[i]>=0) {
                    number -= coins[i];
                    ++total;
                    i--;
                }
            }
        }
        System.out.println(total);
    }
}
