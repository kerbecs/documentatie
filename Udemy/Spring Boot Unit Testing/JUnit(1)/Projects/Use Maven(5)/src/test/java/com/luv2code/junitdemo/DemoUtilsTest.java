package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoUtilsTest {
    DemoUtils demoUtils;

    @Test
    @DisplayName("Equals and Not Equals")
    @Order(1)
    void testEqualsAndNotEquals(){
        Assertions.assertEquals(6,demoUtils.add(2,4),"2+4 = 6");
        Assertions.assertNotEquals(6,demoUtils.add(5,4),"5 + 4 != 9");
    }
    @Test
    @DisplayName("Null and Not Null")
    @Order(2)
    void testNullAndNotNull(){
        String s1 = "hello";
        String s2 = null;

        Assertions.assertNull(demoUtils.checkNull(demoUtils.checkNull(s2)),"Object should be null");
        Assertions.assertNotNull(demoUtils.checkNull(demoUtils.checkNull(s1)),"Object should  be null");
    }
    @Test
    @DisplayName("Same and Not the Same")
    @Order(3)
    void testIfObjectsAreOrNotTheSame(){
        String s = "test String";
        Assertions.assertSame(demoUtils.getAcademyDuplicate(),demoUtils.getAcademy(),"Objects are not the same");
        Assertions.assertNotSame(s,demoUtils.getAcademy(),"Objects are the same");
    }
    @Test
    @DisplayName("True and False")
    @Order(4)
    void testTrueFalse(){
        int one = 10;
        int two = 2;

        Assertions.assertTrue(demoUtils.isGreater(one,two),"Not greater");
        Assertions.assertFalse(demoUtils.isGreater(two,one),"Greater");
    }
    @Test
    @DisplayName("Test Arrays")
    @Order(5)
    void testArrays(){
        String[] array1 = {"A","B","C"};

        Assertions.assertArrayEquals(array1,demoUtils.getFirstThreeLettersOfAlphabet(),"Arrays are not equal");
    }
    @Test
    @DisplayName("Test Iterable")
    @Order(6)
    void testIterable(){
        List<String> list = List.of("luv","2","code");

        Assertions.assertIterableEquals(list,demoUtils.getAcademyInList(),"Lists are different");
    }
    @Test
    @DisplayName("Test Lines")
    @Order(7)
    void testLines(){
        List<String> list = List.of("luv","2","code");

        Assertions.assertLinesMatch(list,demoUtils.getAcademyInList(),"Lists are different");
    }
    @Test
    @DisplayName("Test Throws")
    @Order(8)
    void testThrowsAndDoesNotThrows(){
        Assertions.assertThrows(Exception.class,()->demoUtils.throwException(-2),"This number is negative");
        Assertions.assertDoesNotThrow(()->demoUtils.throwException(5),"No exception");
    }
    @Test
    @DisplayName("Test Timeout")
    @Order(9)
    void testTimeout(){
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3),()->{demoUtils.checkTimeout();},"Method should be executed in at must 3 seconds");
    }
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



}
