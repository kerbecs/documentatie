package com.example.demo.service;

import com.example.demo.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();

    Department getById(int id);

    Department save(Department employee);

    boolean deleteById(int id);
}
