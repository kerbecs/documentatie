package com.example.demo.controller;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import com.example.demo.model_assembler.DepartmentModelAssembler;
import com.example.demo.service.DepartmentService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentModelAssembler departmentModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<DepartmentDTO>>> getAll() {
        CollectionModel<EntityModel<DepartmentDTO>> collectionModel = CollectionModel.of(departmentService.getAll()
                .stream()
                .map(DepartmentDTO::new)
                .map(departmentModelAssembler::toModel)
                .toList());
        collectionModel.add(linkTo(methodOn(this.getClass()).getAll()).withSelfRel());

        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<DepartmentDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(
                departmentModelAssembler.toModel(new DepartmentDTO(departmentService.getById(id))),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntityModel<DepartmentDTO>> save(
            @RequestBody @Valid DepartmentDTO departmentDTO, BindingResult bindingResult) {
        checkIfEntityIsValid(bindingResult);
        departmentDTO.setId(null);

        Department department = new Department(departmentDTO);
        return new ResponseEntity<>(departmentModelAssembler.toModel(new DepartmentDTO(departmentService.save(department))),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<DepartmentDTO>> update(
            @PathVariable int id, @RequestBody DepartmentDTO departmentDTO, BindingResult bindingResult) {
        checkIfEntityIsValid(bindingResult);
        departmentDTO.setId(id);

        return new ResponseEntity<>(departmentModelAssembler.toModel(
                new DepartmentDTO(departmentService.save(new Department(departmentDTO)))),
                HttpStatus.OK);
    }

    private void checkIfEntityIsValid(BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ValidationException("You have mistake(s) in your entity's field(s): " +
                    bindingResult.getFieldError().getDefaultMessage());
    }
}
