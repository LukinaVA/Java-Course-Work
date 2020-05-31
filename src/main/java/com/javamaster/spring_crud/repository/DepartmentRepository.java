package com.javamaster.spring_crud.repository;

import com.javamaster.spring_crud.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department findByName(String name);
}
