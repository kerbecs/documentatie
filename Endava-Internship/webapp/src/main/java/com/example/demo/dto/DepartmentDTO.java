package com.example.demo.dto;

import com.example.demo.entity.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DepartmentDTO extends RepresentationModel<DepartmentDTO> {
    private Integer id;

    @NotNull(message = "{error.message.notNull}")
    @NotEmpty(message = "{error.message.notEmpty}")
    @NotBlank(message = "{error.message.notEmpty}")
    private String name;

    @NotNull(message = "{error.message.notNull}")
    @NotEmpty(message = "{error.message.notEmpty}")
    @NotBlank(message = "{error.message.notEmpty}")
    private String location;

    public DepartmentDTO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.location = department.getLocation();
    }

}