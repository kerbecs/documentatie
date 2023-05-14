package com.luv2code.junitdemo;

import org.junit.jupiter.api.Test;


public class DemoTest {

    DemoUtils demoUtils;
    @Test
    void test3(){
        demoUtils = new DemoUtils();
        System.out.println("3. "+demoUtils + "  " + this);
    }
    @Test
    void test1(){
        System.out.println("1. "+demoUtils + "  " + this);
    }

    @Test
    void test2(){
        System.out.println("2. "+demoUtils + "  " + this);
    }
}
