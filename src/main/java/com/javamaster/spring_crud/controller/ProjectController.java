package com.javamaster.spring_crud.controller;

import com.javamaster.spring_crud.entity.Project;
import com.javamaster.spring_crud.exception.ProjectNotFoundException;
import com.javamaster.spring_crud.service.DepartmentService;
import com.javamaster.spring_crud.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private ProjectService projectsService;
    private DepartmentService departmentService;

    @Autowired
    public void setProjectsService(ProjectService projectsService) {
        this.projectsService = projectsService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> list = projectsService.listProjects();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(projectsService.getProject(id), HttpStatus.OK);
        } catch (ProjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Project> getProject(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(projectsService.getProject(name), HttpStatus.OK);
        } catch (ProjectNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }

    @PostMapping(value = "/addProject", consumes = "application/json", produces = "application/json")
    public Project addProject(@RequestBody Project project) {
        String departmentName = project.getDepartment().getName();
        if(departmentService.getDepartment(departmentName) != null){
            project.setDepartment(departmentService.getDepartment(departmentName));
        }
        return projectsService.addProject(project);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") long id) {
        try {
            projectsService.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProjectNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}