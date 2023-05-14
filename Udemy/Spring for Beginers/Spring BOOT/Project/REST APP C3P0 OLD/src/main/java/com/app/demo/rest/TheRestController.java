package com.app.demo.rest;

import com.app.demo.entity.Employee;
import com.app.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TheRestController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return service.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findEmployeeId(@PathVariable int id){
        Employee employee = service.findEmployeeId(id);

        if(employee==null)
            throw new RuntimeException("Invalid Username ID");

        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        service.saveEmployee(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        service.saveEmployee(employee);

        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        if(service.findEmployeeId(id)==null)
           return "Invalid Username id" + id;

        service.deleteEmployee(id);
        return "User with id "+id+" was deleted.";
    }
}
