package com.luv2code.junitdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DemoUtilsTest {
    @Test
    void testEqualsAndNotEquals(){
        DemoUtils demoUtils = new DemoUtils();

        Assertions.assertEquals(6,demoUtils.add(2,4),"2+4 = 6");
        Assertions.assertNotEquals(6,demoUtils.add(5,4),"5 + 4 != 9");
    }
    @Test
    void testNullAndNotNull(){
        DemoUtils demoUtils = new DemoUtils();
        String s1 = "hello";
        String s2 = null;

        Assertions.assertNull(demoUtils.checkNull(demoUtils.checkNull(s2)),"Object should be null");
       Assertions.assertNotNull(demoUtils.checkNull(demoUtils.checkNull(s1)),"Object should  be null");
    }
}
