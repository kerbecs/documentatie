import java.util.Scanner;

public class Loot {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
    int numbers = scanner.nextInt();
    int capacity = scanner.nextInt();
    int data[][] = new int[numbers][2];
    double somme=0;


        for(int i = 0;i<numbers;i++){
        data[i][0] = scanner.nextInt();
        data[i][1] = scanner.nextInt();
                                    }
        if(data.length==1 ){
            if(data[0][1]>capacity)
            somme = ((double)capacity/data[0][1])*data[0][0];
            else
                somme = data[0][0];
        }
        else {
            for (int i = 0; i < numbers-1; i++) {
                for (int x = i + 1; x < numbers; x++) {
                    if ((double)data[i][0]/data[i][1] < (double)data[x][0]/data[x][1]) {
                        int tempPrice = data[i][0];
                        int tempWeight = data[i][1];

                        data[i][0] = data[x][0];
                        data[i][1] = data[x][1];

                        data[x][0] = tempPrice;
                        data[x][1] = tempWeight;
                    }
                }

            }


            for (int i = 0; i < numbers; i++) {
                    if (capacity <= 0)
                        break;
                    double raport1 = (double) capacity / data[i][1];
                    if (raport1 > 1)
                        raport1 = 1;
                    somme += data[i][0]*raport1;
                    capacity -= data[i][1]*raport1;
                    data[i][0] = 0;
                }
            }
      System.out.printf("%.4f",somme);

    }
}