package com.luv2code.springmvc;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
public class GradebookControllerTest {


    private static MockHttpServletRequest request;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private  MockMvc mockMvc;

    @Mock
    private StudentAndGradeService studentAndGradeServiceMock;
    @Autowired
    private StudentDao studentDao;

    @BeforeAll()
    public static void setUp(){
        request = new MockHttpServletRequest();
        request.addParameter("firstname","Eric");
        request.addParameter("lastname","Roby");
        request.addParameter("emailAddress","roby@mail.ru");
    }

   @BeforeEach
   public void beforeEach(){
       jdbcTemplate.execute("insert into student values(1,'Mititiuc','Eduard','edikutsu2002@mail.ru')");
   }
    @AfterEach
    public void cleanData(){
        jdbcTemplate.execute("delete from student");
    }

    @Test
    public void getStudentHttpRequest() throws Exception {
        CollegeStudent student1 = new CollegeStudent("Eric","Roby","roby@mail.ru");
        CollegeStudent student2 = new CollegeStudent("Chad","Darby","darby@mail.ru");
        List<CollegeStudent> collegeStudentList = new ArrayList<>(Arrays.asList(student1,student2));

        when(studentAndGradeServiceMock.getGradebook()).thenReturn(collegeStudentList);
        assertIterableEquals(collegeStudentList,studentAndGradeServiceMock.getGradebook());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk()).andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView,"index");
   }
   @Test
    public void createStudentHttpRequest() throws Exception {
        CollegeStudent studentOne = new CollegeStudent("Eric","Roby","Eryc@gmail.com");
        List<CollegeStudent> collegeStudentList = new ArrayList<>(Arrays.asList(studentOne));
        when(studentAndGradeServiceMock.getGradebook()).thenReturn(collegeStudentList);

        assertIterableEquals(collegeStudentList,studentAndGradeServiceMock.getGradebook());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .param("firstname",request.getParameterValues("firstname"))
                .param("lastname",request.getParameterValues("lastname"))
                .param("emailAddress",request.getParameterValues("emailAddress")))
                .andExpect(status().isOk()).andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView,"index");

        CollegeStudent collegeStudent = studentDao.findByEmailAddress("roby@mail.ru");

        assertNotNull(collegeStudent,"Student is NULL!!!");

   }
   @Test
    public void createStudentAttribute() throws Exception {
        CollegeStudent collegeStudent = new CollegeStudent();
        collegeStudent.setFirstname(request.getParameter("firstname"));
        collegeStudent.setLastname(request.getParameter("lastname"));
        collegeStudent.setEmailAddress(request.getParameter("emailAddress"));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/").flashAttr("student",collegeStudent))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView,"index");
        CollegeStudent collegeStudent1 = studentDao.findByEmailAddress("roby@mail.ru");
        assertNotNull(collegeStudent1);
   }
   @Test
    public void deleteStudentHttpRequest() throws Exception {
        assertTrue(studentDao.findById(1).isPresent());

        MvcResult  mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/delete/student/{id}",1))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView,"index");
        assertFalse(studentDao.findById(1).isPresent());
   }
   @Test
    public void deleteStudentWhoDoesntExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/delete/student/{id}",0))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView,"error");
   }
}
