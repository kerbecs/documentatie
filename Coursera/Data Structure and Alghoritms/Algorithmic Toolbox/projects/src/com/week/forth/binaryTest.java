package com.week.forth;

import java.util.Random;
import java.util.Scanner;

public class binaryTest {
    public static Scanner in = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
        while(true) {
            int arraySize = random.nextInt(1, 40);
            int keyArraySize;
            long[] array = new long[arraySize];
            long[] keysArray;

            int step1 = random.nextInt(1,100);
            int step2 = random.nextInt(1,5);
            array[0] = step1;
            for (int i = 1; i < arraySize; i++) {
                array[i] = array[i-1] + step1;
            }
            keyArraySize = random.nextInt(1, 20);
            keysArray = new long[keyArraySize];
            keysArray[0] = step2;
            for (int i = 1; i < keyArraySize; i++) {
                keysArray[i] = keysArray[i-1] + step2;
            }
            for (int i = 0; i < arraySize - 1; i++) {
                long min = array[i];
                for (int j = i + 1; j < arraySize; j++) {
                    if (array[j] < array[i]) {
                        array[i] = array[j];
                        array[j] = min;
                    }
                }

            }
            for (int i = 0; i < arraySize; i++) {
                System.out.print(array[i]+" ");
            }
            System.out.println();
            long[] array1 = fast(keyArraySize, arraySize, array, keysArray);
            long[] array2 = naive(keyArraySize, arraySize, array, keysArray);

            for (int i = 0; i < keyArraySize; i++) {
                if(array1[i]==-1 && array2[i]==-1)
                    continue;
                if ((array1[i]-1) != array2[i]) {
                    System.out.println("ERROR");
                    System.exit(100);
                }
            }
            System.out.println("Succes");
        }

    }

    public static long[] fast(int keyArraySize, int arraySize, long[] array, long[] keysArray) {
        long[] founded = new long[keyArraySize];
        for (int i = 0; i < keyArraySize; i++) {
            founded[i] = -1;
        }
        for (int i = 0; i < keyArraySize; i++) {
            int start = 1;
            int end = arraySize;
            int middle = (end + start) / 2;

            while (end >= start) {
                if (array[middle - 1] == keysArray[i]) {
                    founded[i] = middle;
                    break;
                } else if (keysArray[i] > array[middle - 1]) {
                    start = middle + 1;
                    middle = (start + end) / 2;
                } else {
                    end = middle - 1;
                    middle = Math.round((float) (start + end) / 2);
                }
            }
        }
        for(int i = 0;i<keyArraySize;i++) {
            if(founded[i]!=-1)
            System.out.print(founded[i] - 1 + " ");
            else
                System.out.print(founded[i]+" ");
        }
        System.out.println();
        return founded;
    }
    public static long[] naive(int keyArraySize,int arraySize,long[] array, long[] keysArray) {
        long[] founded = new long[keyArraySize];
        for (int i = 0; i < keyArraySize; i++) {
            founded[i] = -1;
        }
        for(int i = 0;i<keyArraySize;i++){
            for(int j = 0;j<arraySize;j++){
                if(array[j]==keysArray[i]) {
                    founded[i] = j;
                }
            }
      }
        for(int i = 0;i<keyArraySize;i++)
            System.out.print(founded[i]+" ");
        System.out.println();
        return founded;
    }

    }

