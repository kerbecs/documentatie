package com.example.demo.service;

import com.example.demo.custom_exception.NotFoundedDataException;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImplementation implements DepartmentService {
    private final DepartmentDAO departmentDAO;

    @Override
    public List<Department> getAll() {
        return departmentDAO.findAll();
    }

    @Override
    public Department getById(int id) {
        return departmentDAO.findById(id).orElseThrow(() -> new NotFoundedDataException("Department with id " + id + " doesn't exist"));
    }

    @Override
    public Department save(Department employee) {
        return departmentDAO.save(employee);
    }


    @Override
    public boolean deleteById(int id) {
        getById(id);

        departmentDAO.deleteById(id);
        return true;
    }
}
