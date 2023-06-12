package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.model_assembler.EmployeeModelAssembler;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmployeeService;
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
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final EmployeeModelAssembler employeeModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<EmployeeDTO>>> getAll() {
        CollectionModel<EntityModel<EmployeeDTO>> collectionModel = CollectionModel.of(
                employeeService.getAll()
                        .stream()
                        .map(EmployeeDTO::new)
                        .map(employeeModelAssembler::toModel)
                        .toList());
        collectionModel.add(linkTo(methodOn(this.getClass()).getAll()).withSelfRel());

        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EmployeeDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(employeeModelAssembler.toModel(new EmployeeDTO(employeeService.getById(id))),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntityModel<EmployeeDTO>> save(
            @RequestBody @Valid EmployeeDTO employeeDTO, BindingResult bindingResult) {
        checkIfEntityIsValid(bindingResult);
        employeeDTO.setId(null);

        Employee employee = new Employee(employeeDTO, departmentService.getById(employeeDTO.getDepartmentId()));
        return new ResponseEntity<>(employeeModelAssembler.toModel(new EmployeeDTO(employeeService.save(employee))),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<EmployeeDTO>> update(
            @PathVariable @Valid int id, @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {
        checkIfEntityIsValid(bindingResult);

        Employee employee = new Employee(employeeDTO, departmentService.getById(employeeDTO.getDepartmentId()));
        employee.setId(id);
        return new ResponseEntity<>(employeeModelAssembler.toModel(new EmployeeDTO(employeeService.save(employee))),
                HttpStatus.OK);
    }

    private void checkIfEntityIsValid(BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ValidationException("You have mistake(s) in your entity's field(s): " +
                    bindingResult.getFieldError().getDefaultMessage());
    }
}
