package com.javamaster.spring_crud.service;

import com.javamaster.spring_crud.entity.Employee;
import com.javamaster.spring_crud.exception.EmployeeNotFoundException;
import com.javamaster.spring_crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> listEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(long id) {
        Optional<Employee> optionalApp = employeeRepository.findById(id);
        if (optionalApp.isPresent()) {
            return optionalApp.get();
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmployeeNotFoundException("There is no employee with this ID");
        }
    }
}
