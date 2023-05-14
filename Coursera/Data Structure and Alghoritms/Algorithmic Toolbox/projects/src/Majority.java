import java.util.HashMap;
import java.util.Scanner;

public class Majority {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        final int size = in.nextInt();
        long[] array = new long[size];
        HashMap<Long,Integer> hashMap = new HashMap<>();

        for(int i = 0;i<size;i++)
            array[i] = in.nextLong();

        for(long i : array){
            if(hashMap.get(i)==null)
                hashMap.put(i,1);
            else{
                hashMap.put(i,hashMap.get(i)+1);
            }
        }

        int appear = 0;
        for(long i : hashMap.keySet()){
            if(hashMap.get(i)>size/2) {
                appear = 1;
                break;
            }
        }
        System.out.println(appear);
    }
}
