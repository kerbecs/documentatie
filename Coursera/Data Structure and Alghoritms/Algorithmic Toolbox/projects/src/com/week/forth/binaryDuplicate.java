package com.week.forth;

import java.util.Scanner;

public class binaryDuplicate {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        int arraySize = in.nextInt();
        int keyArraySize;
        long[] array = new long[arraySize];
        long[] keysArray;

        for(int i = 0;i<arraySize;i++)
            array[i] = in.nextInt();

        keyArraySize = in.nextInt();
        keysArray = new long[keyArraySize];
        for(int i = 0;i<keyArraySize;i++)
            keysArray[i] = in.nextInt();


        int start = 0;
        int end = 0;
        int middle = 0;

        for(int i = 0;i<keyArraySize;i++){
            start = 1;
            end = arraySize;
            middle = (end + start)/2;
            int founded = -1;

            while(end>=start){
                if(array[middle-1]==keysArray[i]){
                    founded = middle-1;
                    for(int x = middle-1;x>=1;x--){
                        if(keysArray[i]==array[x-1])
                            founded = x-1;
                        else
                            break;
                    }

                    break;
                }
                else if(keysArray[i]>array[middle-1]){
                    start = middle + 1;
                    middle = (start + end)/2;
                }
                else {
                    end = middle - 1;
                    middle = (start + end)/2;
                }
            }
            System.out.print(founded+" ");
        }
       /* for(long i : founded){
            System.out.print(i+" ");
        } */

    }
}
