package com.javamaster.spring_crud.service;

import com.javamaster.spring_crud.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> listEmployees();
    Employee getEmployee(long id);
    Employee addEmployee(Employee employee);
    void deleteEmployee(long id);
}
