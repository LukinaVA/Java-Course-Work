package com.javamaster.spring_crud.controller;

import com.javamaster.spring_crud.dto.EmployeeDto;
import com.javamaster.spring_crud.entity.Department;
import com.javamaster.spring_crud.entity.Employee;
import com.javamaster.spring_crud.exception.EmployeeNotFoundException;
import com.javamaster.spring_crud.service.DepartmentService;
import com.javamaster.spring_crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public void setEmployeesService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> list = employeeService.listEmployees();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
        } catch (EmployeeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }

    @PostMapping(value = "/addEmployee", consumes = "application/json", produces = "application/json")
    public Employee addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getFirstName(), employeeDto.getPatherName(), employeeDto.getLastName(),
                employeeDto.getPosition(), employeeDto.getSalary());
        Department department = departmentService.getDepartment(employeeDto.getDepartment());
        employee.setDepartment(department);
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmployeeNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
