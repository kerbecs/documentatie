package com.example.demo.model_assembler;

import com.example.demo.controller.DepartmentController;
import com.example.demo.controller.EmployeeController;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class EmployeeModelAssembler implements RepresentationModelAssembler<EmployeeDTO, EntityModel<EmployeeDTO>> {
    @Override
    public EntityModel<EmployeeDTO> toModel(EmployeeDTO employeeDTO) {
        employeeDTO.add(linkTo(methodOn(EmployeeController.class).getById(employeeDTO.getId())).withSelfRel())
                .add(linkTo(methodOn(EmployeeController.class).getAll()).withRel("employees"))
                .add(linkTo(methodOn(DepartmentController.class).getById(employeeDTO.getDepartmentId())).withRel("department"));

        return EntityModel.of(employeeDTO);
    }
}
