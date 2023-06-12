package com.example.demo.aspect;

import com.example.demo.custom_exception.NoUniqueDataException;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class UniqueValueEmployee {
    private final EmployeeService employeeService;

    @Before("execution" +
            "(" +
            "com.example.demo.entity.Employee " +
            "com.example.demo.service.EmployeeServiceImplementation.save" +
            "(" +
            "com.example.demo.entity.Employee" +
            ")" +
            ")")
    public void validateUnique(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Employee employee = (Employee) args[0];

        if (employee.getId() != null)
            return;
        if (employeeService.findByEmail(employee.getEmail()) != null)
            throw new NoUniqueDataException("An employee with email " + employee.getEmail() + " already exists.");
        if (employeeService.findByPhoneNumber(employee.getPhoneNumber()) != null)
            throw new NoUniqueDataException("An employee with phone number " + employee.getPhoneNumber() + " already exists");
    }

}
