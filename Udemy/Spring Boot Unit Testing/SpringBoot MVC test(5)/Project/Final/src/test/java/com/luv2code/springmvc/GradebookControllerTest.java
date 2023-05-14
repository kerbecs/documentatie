package com.luv2code.springmvc;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.repository.HistoryGradeDao;
import com.luv2code.springmvc.repository.MathGradeDao;
import com.luv2code.springmvc.repository.ScienceGradeDao;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.thymeleaf.spring5.expression.Mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SuppressWarnings(value = "unchecked")
public class GradebookControllerTest {


    private static MockHttpServletRequest request;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private  MockMvc mockMvc;

    @Mock
    private StudentAndGradeService studentAndGradeServiceMock;
    @Autowired
    private StudentAndGradeService studentAndGradeService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private MathGradeDao mathGradeDao;
    @Autowired
    private ScienceGradeDao scienceGradeDao;
    @Autowired
    private HistoryGradeDao historyGradeDao;

    @BeforeAll()
    public static void setUp(){
        request = new MockHttpServletRequest();
        request.addParameter("firstname","Eric");
        request.addParameter("lastname","Roby");
        request.addParameter("emailAddress","roby@mail.ru");
    }

   @BeforeEach
   public void beforeEach(){
       jdbcTemplate.execute(environment.getProperty("sql.script.create.student"));
       jdbcTemplate.execute(environment.getProperty("sql.script.create.math.grade"));
       jdbcTemplate.execute(environment.getProperty("sql.script.create.science.grade"));
       jdbcTemplate.execute(environment.getProperty("sql.script.create.history.grade"));

   }
    @AfterEach
    public void cleanData(){
        jdbcTemplate.execute(environment.getProperty("sql.script.delete.student"));
        jdbcTemplate.execute(environment.getProperty("sql.script.delete.math.grade"));
        jdbcTemplate.execute(environment.getProperty("sql.script.delete.science.grade"));
        jdbcTemplate.execute(environment.getProperty("sql.script.delete.history.grade"));

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
   @Test
    public void createGradeServiceMathGrade(){
        assertTrue(studentAndGradeService.createGrade(80.50,1,"math"));

        Iterable<MathGrade> mathGrades = mathGradeDao.findGradeByStudentId(1);
        assertTrue(mathGrades.iterator().hasNext());

   }
   @Test
    public void createGradeServiceScienceGrade(){
        assertTrue(studentAndGradeService.createGrade(90.50,1,"science"));

        Iterable<ScienceGrade> scienceGrades = scienceGradeDao.findScienceGradeByStudentId(1);

        assertTrue(scienceGrades.iterator().hasNext());

   }
    @Test
    public void createGradeServiceHistoryGrade() {
     assertTrue(studentAndGradeService.createGrade(90.5,1,"history"));
     Iterable<HistoryGrade> historyGrades = historyGradeDao.findHistoryGradeByStudentId(1);

     assertTrue(historyGrades.iterator().hasNext());
     }
     @Test
    public void invalidCreateGradeValue(){
         assertFalse(studentAndGradeService.createGrade(120,1,"science"));
         assertFalse(studentAndGradeService.createGrade(80,1,"nothing"));
         assertFalse(studentAndGradeService.createGrade(-10,1,"science"));
         assertFalse(studentAndGradeService.createGrade(-10,33,"science"));

     }
     @Test
    public void deleteGradeServiceGradeId(){
        assertEquals(1,studentAndGradeService.deleteGrade(1,"math"));
        // 1 care e primul parametru din assertEquals e id al studentului ce va fi returnat cand stergerea se va face cu succes
        assertEquals(1,studentAndGradeService.deleteGrade(1,"science"));
        assertEquals(1,studentAndGradeService.deleteGrade(1,"history"));
    }
    @Test
    public void deleteGradeServiceInvalidStudentId(){
        assertEquals(0,studentAndGradeService.deleteGrade(0,"science"));
        assertEquals(0,studentAndGradeService.deleteGrade(1,"literature"));

    }
    @Test
    public void deleteStudentService(){
        Optional<CollegeStudent> optional = studentDao.findById(1);
        assertTrue(optional.isPresent());

        Optional<MathGrade> deleteMathGrade = mathGradeDao.findById(1);
        Optional<HistoryGrade> deleteHistoryGrade = historyGradeDao.findById(1);
        Optional<ScienceGrade> deleteScienceGrade = scienceGradeDao.findById(1);

        assertTrue(deleteMathGrade.isPresent());
        assertTrue(deleteHistoryGrade.isPresent());
        assertTrue(deleteScienceGrade.isPresent());

        studentAndGradeService.deleteStudent(1);

        deleteMathGrade = mathGradeDao.findById(1);
        deleteHistoryGrade = historyGradeDao.findById(1);
        deleteScienceGrade = scienceGradeDao.findById(1);

        assertFalse(deleteMathGrade.isPresent());
        assertFalse(deleteHistoryGrade.isPresent());
        assertFalse(deleteScienceGrade.isPresent());

        optional = studentDao.findById(1);
        assertFalse(optional.isPresent());
    }

    @Test
    public void studentInformation(){
        GradebookCollegeStudent gradebookCollegeStudent = studentAndGradeService.studentInformation(1);
        assertNotNull(gradebookCollegeStudent);
        assertEquals(1,gradebookCollegeStudent.getId());
        assertEquals("Mititiuc",gradebookCollegeStudent.getFirstname());
        assertEquals("Eduard",gradebookCollegeStudent.getLastname());

        assertTrue(gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size() == 1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getScienceGradeResults().size() == 1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getHistoryGradeResults().size() == 1);

    }
    @Test
    public void studentInformationServiceReturnNull(){
    GradebookCollegeStudent gradebookCollegeStudent = studentAndGradeService.studentInformation(0);

    assertNull(gradebookCollegeStudent);
    }
    @Test
    public void studentInformationHttpRequest() throws Exception {
        assertTrue(studentDao.findById(1).isPresent());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/studentInformation/{id}",1))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView,"studentInformation");

    }
    @Test
    public void studentInformationHttpRequestInvalidId() throws Exception {
        assertFalse(studentDao.findById(0).isPresent());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/studentInformation/{id}",0))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView,"error");
    }
    @Test
    public void createValidGradeHttpRequest() throws Exception {
        assertTrue(studentDao.findById(1).isPresent());
        GradebookCollegeStudent student = studentAndGradeService.studentInformation(1);

        assertEquals(1,student.getStudentGrades().getMathGradeResults().size());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/grades")
                .param("grade","85")
                .param("gradeType","math")
                .param("studentId","1"))
                .andExpect(status().isOk()).andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView,"studentInformation");
       student = studentAndGradeService.studentInformation(1);

       assertEquals(2,student.getStudentGrades().getMathGradeResults().size());

    }
    @Test
    public void createAValidGradeHttpRequestWhenStudentDoesNotExistEmptyResponse() throws Exception {
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/grades")
            .param("grade","85")
            .param("gradeType","math")
            .param("studentId","0"))
            .andExpect(status().isOk())
            .andReturn();

    ModelAndView modelAndView = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView,"error");
    }

    @Test
    public void createANonValidGradeHttpRequestGradeTypeDoesNotExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/grades")
                        .param("grade","85")
                        .param("gradeType","literature")
                        .param("studentId","0"))
                .andExpect(status().isOk())
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView,"error");
    }

    @Test
    public void deleteAValidGrade() throws Exception {
        Optional<MathGrade> mathGrade = mathGradeDao.findById(1);

        assertTrue(mathGrade.isPresent());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/grades/{id}/{gradeType}",1,"math"))
                .andExpect(status().isOk())
                .andReturn();

        ModelAndViewAssert.assertViewName(mvcResult.getModelAndView(),"studentInformation");

        mathGrade = mathGradeDao.findById(1);
        assertFalse(mathGrade.isPresent());


    }
    @Test void deleteAValidGradeWithInvalidGradeId() throws Exception {
        Optional<MathGrade> mathGrade = mathGradeDao.findById(2);
        assertFalse(mathGrade.isPresent());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/grades/{id}/{gradeType}",2,"math"))
                .andExpect(status().isOk()).andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView,"error");
    }
    @Test
    void deleteAValidGradeWithInvalidSubject() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/grades/{id}/{gradeType}", 1, "literature"))
                .andExpect(status().isOk()).andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView, "error");
    }
}
