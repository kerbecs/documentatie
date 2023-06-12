package com.example.demo.dto;

import com.example.demo.entity.Employee;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmployeeDTO extends RepresentationModel<EmployeeDTO> {
    private Integer id;

    @NotNull(message = "{error.message.notNull}")
    @NotEmpty(message = "{error.message.notEmpty}")
    @NotBlank(message = "{error.message.notEmpty}")
    private String firstName;

    @NotNull(message = "{error.message.notNull}")
    @NotEmpty(message = "{error.message.notEmpty}")
    @NotBlank(message = "{error.message.notEmpty}")
    private String lastName;

    @NotNull(message = "{error.message.notNull}")
    private Integer departmentId;

    @NotNull(message = "{error.message.notNull}")
    @NotEmpty(message = "{error.message.notEmpty}")
    @NotBlank(message = "{error.message.notEmpty}")
    private String email;

    @NotNull(message = "${error.message.notNull}")
    private Long phoneNumber;

    @NotNull(message = "{error.message.notNull}")
    @Min(value = 1, message = "{error.message.invalidSalary}")
    private Double salary;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.departmentId = employee.getDepartment().getId();
        this.email = employee.getEmail();
        this.phoneNumber = employee.getPhoneNumber();
        this.salary = employee.getSalary();
    }
}
