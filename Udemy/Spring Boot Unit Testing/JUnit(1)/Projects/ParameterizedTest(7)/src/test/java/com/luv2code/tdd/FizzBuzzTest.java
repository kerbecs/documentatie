package com.luv2code.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {

    @DisplayName("Divisible by Three")
    @Test
    @Order(1)
    void testForDivisibleByThree(){
        String expected = "Fizz";

        assertEquals(expected,FizzBuzz.compute(3),"Should return Fizz");
    }
    @DisplayName("Divisible by Five")
    @Test
    @Order(2)
    void testForDivisibleByFive(){
        String expected = "Buzz";

        assertEquals(expected,FizzBuzz.compute(5),"Should return Buzz");
    }
    @DisplayName("Divisible by Three and Five")
    @Test
    @Order(3)
    void testForDivisibleByThreeAndFive(){
        String expected = "FizzBuzz";

        assertEquals(expected,FizzBuzz.compute(15),"Should return Buzz");
    }
    @DisplayName("Divisible by Five")
    @Test
    @Order(4)
    void testForNotDivisibleByThreeOrFive(){
        String expected = "1";

        assertEquals(expected,FizzBuzz.compute(1),"Should return Buzz");
    }

    @Order(5)
    @DisplayName("Test with a small csv file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    public void CsvSmallFileTest(int value,String valueExpected){
        assertEquals(valueExpected,FizzBuzz.compute(value));

    }
    @Order(6)
    @DisplayName("Test with a medium csv file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources = "/medium-test-data.csv")
    public void CsvMediumFileTest(int value,String valueExpected){
        assertEquals(valueExpected,FizzBuzz.compute(value));

    }
    @Order(7)
    @DisplayName("Test with a big csv file")
    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources = "/large-test-data.csv")
    public void CsvLargeFileTest(int value,String valueExpected){
        assertEquals(valueExpected,FizzBuzz.compute(value));

    }
    @Order(8)
    @DisplayName("Test value source")
    @ParameterizedTest(name = "value={0}, expected={1}")
    @ValueSource(strings = {"a","B","ABC","Aabf","ddfdfd"})
    public void valueSourceTest(String s){
        assertTrue(Character.isLowerCase(s.charAt(0)));
    }

    @Order(9)
    @DisplayName(value = "Test method source")
    @ParameterizedTest(name = "value= {0}, expected={1}")
    @MethodSource("getList")
    public void methodSourceTest(int i,String e){
        assertEquals(e,FizzBuzz.compute(i));
    }
    static String[][] getList(){
        return new String[][]{{"1","1"},{"2","2"},{"3","Fizz"},{"4","4"},{"5","Buzz"}};
        }

}

