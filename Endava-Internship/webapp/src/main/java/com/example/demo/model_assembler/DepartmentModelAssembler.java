package com.example.demo.model_assembler;

import com.example.demo.controller.DepartmentController;
import com.example.demo.dto.DepartmentDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DepartmentModelAssembler implements RepresentationModelAssembler<DepartmentDTO, EntityModel<DepartmentDTO>> {
    @Override
    public EntityModel<DepartmentDTO> toModel(DepartmentDTO departmentDTO) {
        departmentDTO.add(linkTo(methodOn((DepartmentController.class)).getById(departmentDTO.getId())).withSelfRel())
                .add(linkTo(methodOn(DepartmentController.class).getAll()).withRel("departments"));

        return EntityModel.of(departmentDTO);
    }
}
