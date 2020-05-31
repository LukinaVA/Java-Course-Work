package com.javamaster.spring_crud.service;

import com.javamaster.spring_crud.entity.Department;
import com.javamaster.spring_crud.exception.DepartmentNotFoundException;
import com.javamaster.spring_crud.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> listDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }

    @Override
    public Department getDepartment(long id) {
        Optional<Department> optionalApp = departmentRepository.findById(id);
        if (optionalApp.isPresent()) {
            return optionalApp.get();
        } else {
            throw new DepartmentNotFoundException("Department not found");
        }
    }

    @Override
    public Department getDepartment(String name) {
        Optional<Department> optionalApp = Optional.ofNullable(departmentRepository.findByName(name));
        if (optionalApp.isPresent()) {
            return optionalApp.get();
        } else {
            throw new DepartmentNotFoundException("Department not found");
        }
    }

    @Override
    public Department addDepartments(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(long id) {
        try {
            departmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new DepartmentNotFoundException("There is no department with this ID");
        }
    }
}
