package dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        int nr = in.nextInt();
        int[] values = new int[nr+1];
        List<Integer>arrays = new ArrayList<>();

        for(int i = 2;i<=nr;i++)
            values[i] = nr;

        for(int i = 2;i<=nr;i++){
            if(i%2==0){
                values[i] = values[i/2]+1;
            }
            if(i%3==0 && values[i]>values[i/3]+1){
                values[i] = values[i/3]+1;
            }
            if(values[i]>values[i-1]+1){
               values[i] = values[i-1]+1;
            }
        }

        arrays.add(0,nr);
        while(nr>1){
            if(values[nr]==values[nr-1]+1) {
                nr--;
            }
            else if(nr%2==0 && values[nr/2]==values[nr]-1) {
                nr /= 2;
            }
            else {
                nr /= 3;
            }
            arrays.add(0,nr);
        }
        System.out.println(arrays.size()-1);
        for(int i : arrays)
            System.out.print(i+" ");

    }
}
// 34
// (3*3 + 2)*3 + 1 = (9+2)*3 + 1 = 11*3 + 1 = 34
// 1 3 9 10 11 33 34

// 34
//