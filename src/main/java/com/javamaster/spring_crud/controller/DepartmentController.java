package com.javamaster.spring_crud.controller;

import com.javamaster.spring_crud.entity.Department;
import com.javamaster.spring_crud.exception.DepartmentNotFoundException;
import com.javamaster.spring_crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> list = departmentService.listDepartments();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(departmentService.getDepartment(id), HttpStatus.OK);
        } catch (DepartmentNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Department> getDepartment(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(departmentService.getDepartment(name), HttpStatus.OK);
        } catch (DepartmentNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }

    @PostMapping(value = "/addDepartment", consumes = "application/json", produces = "application/json")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.addDepartments(department);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") long id) {
        try {
            departmentService.deleteDepartment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DepartmentNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}