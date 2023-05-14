package com.luv2code.springmvc.service;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.repository.HistoryGradeDao;
import com.luv2code.springmvc.repository.MathGradeDao;
import com.luv2code.springmvc.repository.ScienceGradeDao;
import com.luv2code.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    @Qualifier("mathGrades")
    private MathGrade mathGrade;
    @Autowired
    @Qualifier("scienceGrades")
    private ScienceGrade scienceGrade;
    @Autowired
    @Qualifier("historyGrades")
    private HistoryGrade historyGrade;
    @Autowired
    private MathGradeDao mathGradeDao;
    @Autowired
    private ScienceGradeDao scienceGradeDao;
    @Autowired
    private HistoryGradeDao historyGradeDao;
    @Autowired
    private StudentGrades studentGrades;

    public void createStudent(String firstName,String lastName,String email){
        CollegeStudent student = new CollegeStudent(firstName,lastName,email);
        student.setId(0);
        studentDao.save(student);

    }
    public boolean checkIfStudentIsNull(int id){
        Optional<CollegeStudent> optional = studentDao.findById(id);
        return optional.isPresent();

    }

    public void deleteStudent(int id){
        studentDao.deleteById(id);
        mathGradeDao.deleteByStudentId(id);
        scienceGradeDao.deleteByStudentId(id);
        historyGradeDao.deleteByStudentId(id);

    }

    public Iterable<CollegeStudent> getGradebook(){
        return studentDao.findAll();
    }

    public boolean createGrade(double grade, int studentId,String type){

        if(!checkIfStudentIsNull(studentId)) {
            return false;
        }


       if(grade>= 0 && grade<=100)  {
           if(type.equals("math")) {
               mathGrade = new MathGrade();
               mathGrade.setGrade(grade);
               mathGrade.setStudentId(studentId);
               mathGradeDao.save(mathGrade);
               return true;
           }
           else if(type.equals("science")){
               scienceGrade = new ScienceGrade();
               scienceGrade.setGrade(grade);
               scienceGrade.setStudentId(studentId);
               scienceGradeDao.save(scienceGrade);
               return true;
           }
           else if(type.equals("history")){
               historyGrade = new HistoryGrade();
               historyGrade.setGrade(grade);
               historyGrade.setStudentId(studentId);
               historyGradeDao.save(historyGrade);
               return true;
           }
       }
        return false;
    }
    public int deleteGrade(int id,String type){
        int studentId = 0;
        if(type.equals("math")){
            Optional <MathGrade> grade = mathGradeDao.findById(id);
            if(!grade.isPresent())
                return studentId;
            studentId = grade.get().getStudentId();
            mathGradeDao.deleteById(id);
        }
        else if(type.equals("science")){
            Optional<ScienceGrade> grade = scienceGradeDao.findById(id);
            if(!grade.isPresent())
                return studentId;
            studentId = grade.get().getStudentId();
            scienceGradeDao.deleteById(id);
        }
        else if(type.equals("history")){
            Optional<HistoryGrade> grade = historyGradeDao.findById(id);
            if(!grade.isPresent())
                return studentId;
            studentId = grade.get().getStudentId();
            historyGradeDao.deleteById(id);
        }

        return studentId;
    }

    public GradebookCollegeStudent studentInformation(int id) {
      Optional<CollegeStudent> collegeStudent = studentDao.findById(id);
      if(!collegeStudent.isPresent())
          return null;
       Iterable<MathGrade> mathGrades = mathGradeDao.findGradeByStudentId(id);
        Iterable<HistoryGrade> historyGrades = historyGradeDao.findHistoryGradeByStudentId(id);
        Iterable<ScienceGrade> scienceGrades = scienceGradeDao.findScienceGradeByStudentId(id);

        List<Grade> mathGradesList = new ArrayList<>();
        mathGrades.forEach(mathGradesList::add);

        List<Grade> historyGradesList = new ArrayList<>();
        historyGrades.forEach(historyGradesList::add);

        List<Grade> scienceGradesList = new ArrayList<>();
        scienceGrades.forEach(scienceGradesList::add);

        studentGrades.setHistoryGradeResults(historyGradesList);
        studentGrades.setScienceGradeResults(scienceGradesList);
        studentGrades.setMathGradeResults(mathGradesList);

        GradebookCollegeStudent gradebookCollegeStudent = new GradebookCollegeStudent(
                collegeStudent.get().getId(),collegeStudent.get().getFirstname(),collegeStudent.get().getLastname(),collegeStudent.get().getEmailAddress()
        ,studentGrades);

        return gradebookCollegeStudent;
    }
    public void configureStudentInformationModel(int studentId, Model m){
        GradebookCollegeStudent student = this.studentInformation(studentId);
        m.addAttribute("student",student);
        if(student.getStudentGrades().getMathGradeResults().size()>0){
            m.addAttribute("mathAverage",student.getStudentGrades().findGradePointAverage(student.getStudentGrades().getMathGradeResults()));

        }
        else
            m.addAttribute("mathAverage","N/A");
        if(student.getStudentGrades().getScienceGradeResults().size()>0){
            m.addAttribute("scienceAverage",student.getStudentGrades().findGradePointAverage(student.getStudentGrades().getScienceGradeResults()));

        }
        else
            m.addAttribute("scienceAverage","N/A");
        if(student.getStudentGrades().getHistoryGradeResults().size()>0){
            m.addAttribute("historyAverage",student.getStudentGrades().findGradePointAverage(student.getStudentGrades().getHistoryGradeResults()));

        }
        else
            m.addAttribute("historyAverage","N/A");
    }
}
