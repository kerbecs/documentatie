package com.luv2code.springmvc.repository;

import com.luv2code.springmvc.models.ScienceGrade;
import com.luv2code.springmvc.models.StudentGrades;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScienceGradeDao extends JpaRepository<ScienceGrade, Integer> {
    Iterable<ScienceGrade> findScienceGradeByStudentId(int id);
    void deleteByStudentId(int id);
}
