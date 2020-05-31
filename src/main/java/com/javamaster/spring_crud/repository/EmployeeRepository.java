package com.javamaster.spring_crud.repository;

import com.javamaster.spring_crud.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}