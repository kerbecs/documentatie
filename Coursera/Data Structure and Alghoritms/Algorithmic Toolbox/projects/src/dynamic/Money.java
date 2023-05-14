package dynamic;

import java.util.Scanner;

public class Money {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        int number = in.nextInt();
        int[][] coins = new int[number+1][number+1];
        int[] moneyType = {1,3,4};

        for(int i = 0;i<=number;i++){
            coins[0][i] = i;
        }

        coins[1][1] = 1;

        for(int i = 1;i<=number;i++){
          int nrOfCoins1 = 0;
          int nrOfCoins2 = 0;
          int nrOfCoins3 = 0;

            if(coins[0][i]>=moneyType[0]){
                nrOfCoins1 = coins[0][i];
            }
            if(coins[0][i]>=moneyType[1]){
                nrOfCoins2 = 1;
                nrOfCoins2 += coins[1][i-moneyType[1]];
            }
            if(coins[0][i]>=moneyType[2]){
                nrOfCoins3 = 1;
                nrOfCoins3 += coins[1][i-moneyType[2]];
            }
            if(nrOfCoins2==0 && nrOfCoins3==0 && nrOfCoins1!=0)
                coins[1][i] = nrOfCoins1;
            else if(nrOfCoins3 == 0 && nrOfCoins1 != 0)
                coins[1][i] = Math.min(nrOfCoins1,nrOfCoins2);
            else if(nrOfCoins1 != 0 && nrOfCoins2 != 0)
                coins[1][i] = Math.min(nrOfCoins1,Math.min(nrOfCoins2,nrOfCoins3));
        }
            System.out.println(coins[1][number]);
    }
}
