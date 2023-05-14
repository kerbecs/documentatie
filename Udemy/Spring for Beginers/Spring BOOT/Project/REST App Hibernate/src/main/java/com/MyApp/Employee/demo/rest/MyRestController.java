package com.MyApp.Employee.demo.rest;

import com.MyApp.Employee.demo.entity.Employee;
import com.MyApp.Employee.demo.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeServiceImpl service;

    @GetMapping("/employees")
    public List<Employee> employeeList(){

        return service.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee employeeById(@PathVariable int id){
        Employee employee = service.findById(id);
        if(employee==null)
            throw new RuntimeException("Not found!Id: "+id);
        return employee;
    }

    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee employee){
        employee.setId(null);
        service.save(employee);

       return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        service.save(employee);

        return employee;
    }
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        Employee theEmployee = service.findById(id);
        if(theEmployee==null)
            return "Employee with id "+id + " doesn't exist";

        service.deleteById(id);
        return "Deleted employee with id "+id;
    }
}
