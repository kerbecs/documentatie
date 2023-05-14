package dynamic;

import java.util.Scanner;

public class EditDistance {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String string1 = in.nextLine();
        String string2 = in.nextLine();
        editDistance(string1,string2);
    }
    public static void editDistance(String s1,String s2){
        int[][] D = new int[s1.length()+1][s2.length()+1];
        for(int i = 0;i<=s1.length();i++)
            D[i][0] = i;
        for(int j = 0;j<=s2.length();j++)
            D[0][j] = j;

        for(int j = 1;j<=s2.length();j++){
            for(int i = 1;i<=s1.length();i++){
                int insertion = D[i][j-1] + 1;
                int deletion = D[i-1][j] + 1;
                int match = D[i-1][j-1];
                int mismatch = D[i-1][j-1] + 1;

                if(s1.charAt(i-1)==s2.charAt(j-1))
                    D[i][j] = Math.min(insertion,Math.min(deletion,match));
                else
                    D[i][j] = Math.min(insertion,Math.min(deletion,mismatch));
            }
        }
        for(int i = 0;i<D.length;i++){
            for(int j = 0;j<D[0].length;j++)
                System.out.print(D[i][j]+" ");
            System.out.println();
        }
System.out.println(D[s1.length()][s2.length()]);
    }
}
