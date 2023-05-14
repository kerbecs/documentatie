package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

public class DemoUtilsTest {
    DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach(){
     demoUtils = new DemoUtils();
     System.out.println("@BeforeEach executed before the execution of each test");
    }

    @AfterEach
    void tearDownAfterEach(){
        System.out.println("Running @AfterEach");
    }

    @BeforeAll
    static void setupBeforeAll(){
        System.out.println("@BeforeAll executes only once before all tests from the class");
    }

    @AfterAll
    static void setupAfterAll(){
        System.out.println("@AfterAll executes oly before all tests from the class");
    }

    @Test
    void testEqualsAndNotEquals(){
        Assertions.assertEquals(6,demoUtils.add(2,4),"2+4 = 6");
        Assertions.assertNotEquals(6,demoUtils.add(5,4),"5 + 4 != 9");
    }
    @Test
    void testNullAndNotNull(){
        String s1 = "hello";
        String s2 = null;

        Assertions.assertNull(demoUtils.checkNull(demoUtils.checkNull(s2)),"Object should be null");
       Assertions.assertNotNull(demoUtils.checkNull(demoUtils.checkNull(s1)),"Object should  be null");
    }
}
