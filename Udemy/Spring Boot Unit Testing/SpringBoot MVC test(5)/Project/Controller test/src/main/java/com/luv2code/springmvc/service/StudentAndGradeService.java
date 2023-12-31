package com.luv2code.springmvc.service;

import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {
    @Autowired
    private StudentDao studentDao;
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
    }

    public Iterable<CollegeStudent> getGradebook(){
        return studentDao.findAll();
    }
}
