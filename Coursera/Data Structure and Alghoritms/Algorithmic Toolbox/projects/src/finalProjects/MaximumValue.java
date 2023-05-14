package finalProjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MaximumValue {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        StringBuilder expression = new StringBuilder(in.nextLine());
        List<Long> numbers = new ArrayList<>();
        List<Character> operations = new ArrayList<>();
        getNumbersAndOperations(expression, numbers, operations);


        Long[][] Minarray = new Long[numbers.size()][numbers.size()];
        Long[][] Maxarray = new Long[numbers.size()][numbers.size()];

        for (int i = 0; i < Minarray.length; i++) {
            Minarray[i][i] = numbers.get(i);
        }
        for (int x = 0; x < Minarray.length; x++) {
            for (int y = 0; y < Minarray[0].length; y++) {
                if (Minarray[x][y] == null || Maxarray[x][y]==null) {
                    Minarray[x][y] = 0L;
                    Maxarray[x][y] = 0L;
                }
            }
        }

        //--------------------------------------------------------------
        for(int i = 0;i<Minarray.length;i++){
            Minarray[i][i] = numbers.get(i);
            Maxarray[i][i] = numbers.get(i);
        }

        for (int s = 1; s <= Minarray.length-1; s++) {
            for (int i = 1; i <= Minarray.length - s; i++) {
                int j = i+s;
                long[] minMax = getMaxAndMin(i,j,Minarray,Maxarray,operations);
                Minarray[i-1][j-1] = minMax[0];
                Maxarray[i-1][j-1] = minMax[1];
            }
        }

        System.out.println(Maxarray[0][Maxarray.length-1]);

    }

    public static List<Long> getNumbersAndOperations(StringBuilder expression, List<Long> numbersList, List<Character> operations) {

        StringBuilder number = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) <= '9' && expression.charAt(i) >= '0') {
                number.append(expression.charAt(i));
            } else {
                numbersList.add(Long.parseLong(number.toString()));
                number = new StringBuilder();
                operations.add(expression.charAt(i));
            }
        }
        numbersList.add(Long.parseLong(number.toString()));

        return numbersList;
    }

    public static long[] getMaxAndMin(int i,int j,Long[][]Minarray,Long[][]Maxarray,List<Character> operations){
      long[] minMax = new long[2];
      minMax[0] = Long.MAX_VALUE;
      minMax[1] = Long.MIN_VALUE;

        long a = 0,b = 0,c = 0,d = 0;
        for(int k = i;k<=j-1;k++){
          if(operations.get(k-1)=='+'){
              a = Minarray[i-1][k-1] + Maxarray[k][j-1];
              b = Minarray[i-1][k-1] + Minarray[k][j-1];
              c = Maxarray[i-1][k-1] + Maxarray[k][j-1];
              d = Maxarray[i-1][k-1] + Minarray[k][j-1];
          }
          else if(operations.get(k-1)=='-'){
              a = Minarray[i-1][k-1] - Maxarray[k][j-1];
              b = Minarray[i-1][k-1] - Minarray[k][j-1];
              c = Maxarray[i-1][k-1] - Maxarray[k][j-1];
              d = Maxarray[i-1][k-1] - Minarray[k][j-1];
          }
          else if(operations.get(k-1)=='*'){
              a = Minarray[i-1][k-1] * Maxarray[k][j-1];
              b = Minarray[i-1][k-1] * Minarray[k][j-1];
              c = Maxarray[i-1][k-1] * Maxarray[k][j-1];
              d = Maxarray[i-1][k-1] * Minarray[k][j-1];
          }
          minMax[0] = Math.min(minMax[0],Math.min(a,Math.min(b,Math.min(c,d))));
          minMax[1] = Math.max(minMax[1],Math.max(a,Math.max(b,Math.max(c,d))));
      }
      return minMax;
    }

}
//521*75+6-8*10
