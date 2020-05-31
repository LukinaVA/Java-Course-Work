package com.javamaster.spring_crud.repository;

import com.javamaster.spring_crud.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findByName(String name);
}
