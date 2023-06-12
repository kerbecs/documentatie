package com.example.demo.entity;

import com.example.demo.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "salary")
    private Double salary;

    public Employee(EmployeeDTO employeeDTO,Department department){
        this.firstName = employeeDTO.getFirstName();
        this.lastName = employeeDTO.getLastName();
        this.department = department;
        this.email = employeeDTO.getEmail();
        this.phoneNumber = employeeDTO.getPhoneNumber();
        this.salary = employeeDTO.getSalary();
    }
}
