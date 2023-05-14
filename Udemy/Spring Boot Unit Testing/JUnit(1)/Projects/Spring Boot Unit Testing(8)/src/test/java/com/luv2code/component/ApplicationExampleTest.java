package com.luv2code.component;

import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
public class ApplicationExampleTest {

    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.school.name}")
    private String schoolName;

    @Value("${info.app.description}")
    private String appDescription;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEach(){
        count += 1;
        System.out.println("Testing: "+appInfo+" which is "+
                appDescription+" Execution of test method "+
                count);
        student.setFirstname("Mititiuc");
        student.setLastname("Eduard");
        student.setEmailAddress("edikutsu2002@mail.ru");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0,85.0,76.5,91.75)));
        student.setStudentGrades(studentGrades);
    }

    @DisplayName("Add grade results for student grades")
    @Test
    public void addGradeResultForStudentGrades(){
        assertEquals(353.25,studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));
    }
    @DisplayName("Add grade results for student grades not equal")
    @Test
    public void addGradeResultsForStudentGradesAssertNotEquals(){
        assertEquals(0,studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));

    }

    @DisplayName("Is grade greater")
    @Test
    public void isGradeGreaterStudentGrades(){
        assertTrue(studentGrades.isGradeGreater(90,75));
    }
    @DisplayName("Is grade greater false")
    @Test
    public void isGradeGreaterFalseStudentGrades(){
        assertFalse(studentGrades.isGradeGreater(89,92));
    }

    @DisplayName("Check Null for student grades")
    @Test
    public void checkNullForStudentGrades(){
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()),"should not be null");

    }

    @DisplayName("Create student without grade init")
    @Test
    public void createStudentWithoutGradesInit(){
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        assertNotNull(studentTwo);
        assertNull(studentGrades.checkNull(studentTwo.getStudentGrades()));
    }

    @DisplayName("Verify student are prototype")
    @Test
    public void verifyIfStudentsArePrototype(){
        CollegeStudent s1 = context.getBean("collegeStudent", CollegeStudent.class);
        CollegeStudent s2 = context.getBean("collegeStudent", CollegeStudent.class);
        assertNotSame(s1,s2);
    }
    @DisplayName("Find Grade Point Average")
    @Test
    public void findGradePointAverage(){
        assertAll("Testing all assertEquals",()->assertEquals(353.25,studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults())),
                ()->assertEquals(88.31,studentGrades.findGradePointAverage(
                        student.getStudentGrades().getMathGradeResults()
                )));
    }
}
