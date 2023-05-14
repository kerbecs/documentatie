package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ReflectionTest {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    CollegeStudent collegeStudent1;

    @Autowired
    StudentGrades studentGrades;

    @Test
    public void getPrivateField(){
        assertEquals(100,ReflectionTestUtils.getField(collegeStudent1,"id"));
    }

    @Test
    public void invokePrivateMethod(){
        assertEquals("Mititiuc100",ReflectionTestUtils.invokeMethod(collegeStudent1,"getFirstNameAndId"));
    }


    @BeforeEach
    public void studentBeforeEach(){
        collegeStudent1.setFirstname("Mititiuc");
        collegeStudent1.setLastname("Eduard");
        collegeStudent1.setEmailAddress("edikutsu2002@mail.ru");
        collegeStudent1.setStudentGrades(studentGrades);

        ReflectionTestUtils.setField(collegeStudent1,"id",100);
        ReflectionTestUtils.setField(collegeStudent1,"studentGrades",new StudentGrades(new ArrayList<>(Arrays.asList(100.0,85.0,76.50,91.75))));
    }
}
