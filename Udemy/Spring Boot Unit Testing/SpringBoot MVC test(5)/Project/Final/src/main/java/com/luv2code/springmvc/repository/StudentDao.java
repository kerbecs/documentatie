package com.luv2code.springmvc.repository;

import com.luv2code.springmvc.models.CollegeStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StudentDao extends JpaRepository<CollegeStudent, Integer> {
CollegeStudent findByEmailAddress(String emailAddress);
}
