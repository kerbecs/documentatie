package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {MvcTestingExampleApplication.class})
@TestPropertySource("/application-test.properties")
public class StudentAndGradeServiceTest {

    @Autowired
    StudentAndGradeService studentService;
    @Autowired
    StudentDao studentDao;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void addData(){
     jdbcTemplate.execute("insert into student values(1,'Mititiuc','Eduard','edikutsu2002@mail.ru')");
    }
    @AfterEach
    public void cleanData(){
        jdbcTemplate.execute("delete from student");
    }
    @Test
    public void createStudentService(){
        studentService.createStudent("Mititiuc","Eduard","edikutsu2002@mail.ru");
        CollegeStudent student = studentDao.findByEmailAddress("edikutsu2002@mail.ru");

        assertEquals("edikutsu2002@mail.ru",student.getEmailAddress(),"Find by email");

    }
    @Test
    public void isStudentNullCheck(){
        assertTrue(studentService.checkIfStudentIsNull(1));
        assertFalse(studentService.checkIfStudentIsNull(0));
    }
    @Test
    public void deleteStudentService(){
        Optional<CollegeStudent> optional = studentDao.findById(1);
        assertTrue(optional.isPresent());

        studentService.deleteStudent(1);

        optional = studentDao.findById(1);
        assertFalse(optional.isPresent());
    }
    @Sql("/insertData.sql")
    @Test
    public void getGradebookService(){
        Iterable<CollegeStudent> iterable = studentService.getGradebook();
        List<CollegeStudent> collegeStudentList = new ArrayList<>();

        for(CollegeStudent collegeStudent : iterable){
            collegeStudentList.add(collegeStudent);
        }
        assertEquals(5,collegeStudentList.size());
    }


}



