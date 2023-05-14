package finalProjects;

import java.util.Arrays;
import java.util.Scanner;

public class Gold {
    public static Scanner in = new Scanner(System.in);
    private static int bagCapacity = 0;
    public static int[] golds;
    public static int goldAmount;

    public static int[][] matrix;

    public static void main(String[] args) {
        bagCapacity = in.nextInt();
        goldAmount = in.nextInt();

        golds = new int[goldAmount];

        for (int i = 0; i < goldAmount; i++)
            golds[i] = in.nextInt();

        matrix = new int[goldAmount+1][bagCapacity+1];

        Arrays.fill(matrix[0], 0);

        for(int i = 0;i<matrix.length;i++)
            matrix[i][0] = 0;


        for(int x = 1;x<matrix.length;x++){
            for(int y = 1;y<matrix[0].length;y++){
                matrix[x][y] = matrix[x-1][y];
                if(golds[x-1]<=y) {
                    int value = matrix[x - 1][y - golds[x-1]] + golds[x - 1];
                    if (matrix[x][y] < value)
                        matrix[x][y] = value;
                }
            }
        }

        System.out.println(matrix[matrix.length-1][matrix[0].length-1]);
    }
}
