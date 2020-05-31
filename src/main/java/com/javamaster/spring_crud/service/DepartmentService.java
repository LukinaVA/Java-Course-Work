package com.javamaster.spring_crud.service;

import com.javamaster.spring_crud.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> listDepartments();
    Department getDepartment(long id);
    Department getDepartment(String name);
    Department addDepartments(Department department);
    void deleteDepartment(long id);
}
