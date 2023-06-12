package com.example.demo.entity;

import com.example.demo.dto.DepartmentDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    public Department(DepartmentDTO departmentDTO) {
        this.id = departmentDTO.getId();
        this.name = departmentDTO.getName();
        this.location = departmentDTO.getLocation();
    }

}
