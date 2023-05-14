package com.java.MyApp;

public class test {
    public static void main(String[] args){
        System.out.println(B.NR);
        B obj = new B();
        System.out.println(obj.NR);
    }
}

interface A{
        int NR = 100;
}
class B implements A{
}
