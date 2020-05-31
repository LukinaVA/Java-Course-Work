package com.javamaster.spring_crud.service;

import com.javamaster.spring_crud.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> listProjects();
    Project getProject(long id);
    Project getProject(String name);
    Project addProject(Project project);
    void deleteProject(long id);
}
