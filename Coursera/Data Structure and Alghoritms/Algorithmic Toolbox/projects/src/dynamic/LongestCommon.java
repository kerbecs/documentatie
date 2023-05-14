package dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LongestCommon {
    public static Scanner in = new Scanner(System.in);
    private static String string1;
    private static String string2;
    private static Element[][] path;
    public static void main(String [] args){

        string1 = in.nextLine();
        string2 = in.nextLine();
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
           path[i][0].coords[0] = i;
           path[i][0].coords[1] = 0;
       }
        for(int i = 1;i<len2;i++) {
            path[0][i] = new Element();
            path[0][i].distance = path[0][i-1].distance + 1;
            path[0][i].coords[0] = 0;
            path[0][i].coords[1] = i;
        }


        for(int i = 1;i<len1;i++){
            for(int j = 1;j<len2;j++){
                path[i][j] = new Element();
                compare(path[i][j],new Element[]{path[i-1][j-1],path[i][j-1],path[i-1][j]},new int[]{i,j});
             path[i][j].coords[0] = i;
             path[i][j].coords[1] = j;
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

        int[][] positions = rebuildPath(len1,len2);
        for(int i = 0;i<positions[0].length;i++){
            System.out.print(positions[0][i]+"  ");
        }
        System.out.println();
        for(int i = 0;i<positions[0].length;i++){
            System.out.print(positions[1][i]+"  ");
        }
        createStrings(positions);
    }
    public static void compare(Element current, Element[] elements, int[] coords){
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
            current.parent = elements[0];
        }
        else if(distance2<distance1 && distance2<distance3) {
            current.distance = distance2;
            current.parent = elements[1];
        }
        else if(distance3<distance1 && distance3<distance2) {
            current.distance = distance3;
            current.parent = elements[2];
        }
        else {
            current.distance = distance1;
            current.parent = elements[0];
        }
    }
    public static Element min(int len1, int len2){
         Element minimum = path[len1-1][0];
         for(int i = 1;i<len2;i++){
             if(path[len1-1][i].distance<minimum.distance)
                 minimum = path[len1-1][i];
         }

         return minimum;
    }
    public static int[][] rebuildPath(int len1,int len2){
        Element minimum = min(len1,len2);
        int[][] positions = new int[len1+len2][len1+len2];
        List<Element> originalPath = new ArrayList<>();
        int skipX = 0;
        int skipY = 0;


        Element parent = minimum;

        while(parent!=null){
            System.out.println(parent.distance);
            originalPath.add(0,parent);
            parent = parent.parent;
        }

        if(originalPath.get(1).coords[1]==1 && originalPath.get(1).coords[0]>1){
            skipX = originalPath.get(1).coords[0]-1;
            for(int i = 0;i<skipX;i++) {
                positions[0][i] = -1;
                positions[1][i] = 1;
            }
        }
        if(originalPath.get(1).coords[1]>1 && originalPath.get(1).coords[0]==1){
            skipY = originalPath.get(1).coords[1]-1;
            for(int i = 0;i<skipY;i++) {
                positions[1][i] = -1;
                positions[0][i] = 1;
            }
        }

        for(int i = 1;i<originalPath.size();i++) {
            //parinte mai la dreapta
            if (originalPath.get(i).coords[0] == originalPath.get(i-1).coords[0]) {
                positions[0][i+skipX-1] = -1;
                positions[1][i+skipY-1] = 1;
            }
            //parinte mai in sus
            else if (originalPath.get(i).coords[1] == originalPath.get(i-1).coords[1]) {
                 positions[1][i+skipY-1] = -1;
                 positions[0][i+skipX-1] = 1;
            }
            //parinte oblic
            else if(originalPath.get(i).coords[0] == originalPath.get(i-1).coords[0]+1 && originalPath.get(i).coords[1] == originalPath.get(i-1).coords[1]+1) {
               positions[0][i+skipX-1] = 1;
               positions[1][i+skipY-1] = 1;
            }
        }
        return positions;
    }
    public static void createStrings(int[][]originalPath){
        StringBuffer stringNr1 = new StringBuffer(string2);//pentru stringul vertical
        StringBuffer stringNr2 = new StringBuffer(string1);//pentru stringul orizontal

        for(int i = 0;i<originalPath[0].length;i++){
            if(originalPath[0][i]==-1)
                stringNr1.insert(i," ");
            if(originalPath[1][i]==-1)
                stringNr2.insert(i," ");
            if(originalPath[1][i]==0 && originalPath[0][i]==0)
                break;
        }
        System.out.println();
        System.out.println(stringNr1);
        System.out.println(stringNr2);
    }
}

class Element{
    public Element parent;
    public int[] coords = new int[2];
    public int distance = 0;
}
