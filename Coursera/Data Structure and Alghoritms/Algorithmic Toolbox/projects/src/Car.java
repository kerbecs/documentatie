import java.util.Scanner;

public class Car {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[]args){
        int distance = in.nextInt();
        int maxFuel = in.nextInt();
        int stationsNr = in.nextInt();
        int stops = 0;
        int kms = 0;

        int currentFuel = maxFuel;

        int[] stations = new int[stationsNr+2];

        stations[0] = 0;
        for(int i = 1;i<stationsNr+1;i++){
            stations[i] = in.nextInt();
        }
        stations[stationsNr+1] = distance;

        for(int i = 0;i<stationsNr+1;i++){
            int fuelNecessary = stations[i+1] - stations[i];
            if(fuelNecessary>maxFuel){
                stops = -1;
                break;
            }
            if(fuelNecessary>currentFuel){
                stops++;
                currentFuel = maxFuel;
            }

                currentFuel-= fuelNecessary;
        }

        System.out.println(stops);

    }
}