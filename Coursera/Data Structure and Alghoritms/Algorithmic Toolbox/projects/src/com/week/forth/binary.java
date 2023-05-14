package com.week.forth;

import java.util.Scanner;

public class binary {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        int arraySize = in.nextInt();
        int keyArraySize;
        long[] array = new long[arraySize];
        long[] keysArray;
        long[] founded;

        for(int i = 0;i<arraySize;i++){
            array[i] = in.nextInt();
        }
        keyArraySize = in.nextInt();
        keysArray = new long[keyArraySize];
        for(int i = 0;i<keyArraySize;i++){
            keysArray[i] = in.nextInt();
        }
        founded = new long[keyArraySize];
        for(int i = 0;i<keyArraySize;i++){
            founded[i] = -1;
        }
        int start = 0;
        int end = 0;
        int middle = 0;

        for(int i = 0;i<keyArraySize;i++){
            start = 1;
            end = arraySize;
            middle = (end + start)/2;

            while(end>=start){
                if(array[middle-1]==keysArray[i]){
                    founded[i] = middle;

                    break;
                }
                else if(keysArray[i]>array[middle-1]){
                    start = middle + 1;
                    middle = (start + end)/2;
                }
                else {
                 end = middle - 1;
                 middle = Math.round((float)(start + end)/2);
                }
            }
        }
        for(long i : founded){
            if(i!=-1)
             System.out.print(i-1+" ");
            else
                System.out.print(i+" ");
        }

    }
}
