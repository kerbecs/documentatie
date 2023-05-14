package dynamicModify;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LongestCommonNumber {
    public static Scanner in = new Scanner(System.in);
    private static String string1;
    private static String string2;
    private static Element[][] path;
    public static void main(String [] args){

        string2 = in.nextLine();
        string1 = in.nextLine();
        if(string2.length()>string1.length()){
            String temp = new String(string1);
            string1 = string2;
            string2 = temp;
        }

        final int len1 = string1.length()+1;
        final int len2 = string2.length()+1;
        path = new Element[len1][len2];

        path[0][0] = new Element();
        for(int i = 1;i<len1;i++) {
            path[i][0]=new Element();
            path[i][0].distance = path[i-1][0].distance+1;
        }
        for(int i = 1;i<len2;i++) {
            path[0][i] = new Element();
            path[0][i].distance = path[0][i-1].distance + 1;
        }


        for(int i = 1;i<len1;i++){
            for(int j = 1;j<len2;j++){
                path[i][j] = new Element();
                compare(path[i][j],new Element[]{path[i-1][j-1],path[i][j-1],path[i-1][j]},new int[]{i,j});
            }
        }


        for(int i = 0;i<len1;i++) {
            for (int j = 0; j < len2; j++)
                if(path[i][j]==null)
                    System.out.print(0+"  ");
                else
                    System.out.print(path[i][j].distance+"  ");
            System.out.println();
        }
        System.out.println(min(len1,len2));
    }
    public static void compare(Element current, Element[] elements,int[] coords){
        int distance1 = elements[0].distance;
        int distance2 = elements[1].distance+1; //aceeasi linie
        int distance3 = elements[2].distance+1; //aceeasi coloana

        try {
            if (string1.charAt(coords[0] - 1) != string2.charAt(coords[1] - 1)) {
                distance1++;
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            distance1++;
        }

        if(distance1<distance2 && distance1<distance3) {
            current.distance = distance1;
        }
        else if(distance2<distance1 && distance2<distance3) {
            current.distance = distance2;
        }
        else if(distance3<distance1 && distance3<distance2) {
            current.distance = distance3;
        }
        else {
            current.distance = distance1;
        }
    }
    public static int min(int len1, int len2){
        Element minimum = path[len1-1][0];
        for(int i = 1;i<len2;i++){
            if(path[len1-1][i].distance<minimum.distance)
                minimum = path[len1-1][i];
        }

        return minimum.distance;
    }
}

class Element{
    public int distance = 0;
}
