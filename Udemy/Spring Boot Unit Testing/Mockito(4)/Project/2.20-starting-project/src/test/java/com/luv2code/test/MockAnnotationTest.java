package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class MockAnnotationTest {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    CollegeStudent collegeStudent1;

    @MockBean
    private ApplicationDao applicationDao;

    @Autowired
    private ApplicationService applicationService;

    @DisplayName("When & Verify")
    @Test
    public void assertEqualsTest(){
        when(applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults())).thenReturn(100.0);
        assertEquals(100,applicationService.addGradeResultsForSingleClass(collegeStudent1.getStudentGrades().getMathGradeResults()));

    }

    @DisplayName("Find Gpa")
    @Test
    public void assertEqualTestFindGpa(){
        when(applicationDao.findGradePointAverage(studentGrades.getMathGradeResults())).thenReturn(88.31);

        assertEquals(88.31,applicationService.findGradePointAverage(studentGrades.getMathGradeResults()));
    }
    @DisplayName("Not Null")
    @Test
    public void assertNotNullTest(){
        when(applicationDao.checkNull(studentGrades.getMathGradeResults())).thenReturn(true);

        assertNotNull(applicationService.checkNull(collegeStudent1.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("Throw runtime exception")
    @Test
    public void throwRunTimeError(){
        CollegeStudent collegeStudent = (CollegeStudent) applicationContext.getBean("collegeStudent");
        doThrow(new RuntimeException("Test")).when(applicationDao).checkNull(collegeStudent);

        assertThrows(RuntimeException.class,()->applicationDao.checkNull(collegeStudent));
        verify(applicationDao,times(1)).checkNull(collegeStudent);
    }

    @DisplayName("Multiple calls")
    @Test
    public void ConsecutiveCall(){
        CollegeStudent collegeStudent = (CollegeStudent) applicationContext.getBean("collegeStudent");
        doThrow(new RuntimeException("Opaa")).doReturn("Test String").when(applicationDao).checkNull(collegeStudent);

        assertThrows(RuntimeException.class,()->applicationDao.checkNull(collegeStudent));

        assertEquals("Test String",applicationDao.checkNull(collegeStudent));

    }

    @BeforeEach
    public void beforeEach(){
        collegeStudent1.setFirstname("Mititiuc");
        collegeStudent1.setLastname("Eduard");
        collegeStudent1.setEmailAddress("edikutsu2002@mail.ru");
        collegeStudent1.setStudentGrades(studentGrades);
    }

}
