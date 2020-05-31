package com.javamaster.spring_crud.service;

import com.javamaster.spring_crud.entity.Project;
import com.javamaster.spring_crud.exception.ProjectNotFoundException;
import com.javamaster.spring_crud.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> listProjects() {
        return (List<Project>) projectRepository.findAll();
    }

    @Override
    public Project getProject(long id) {
        Optional<Project> optionalApp = projectRepository.findById(id);
        if (optionalApp.isPresent()) {
            return optionalApp.get();
        } else {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    @Override
    public Project getProject(String name) {
        Optional<Project> optionalApp = Optional.ofNullable(projectRepository.findByName(name));
        if (optionalApp.isPresent()) {
            return optionalApp.get();
        } else {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(long id) {
        try {
            projectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ProjectNotFoundException("There is no project with this ID");
        }
    }
}
