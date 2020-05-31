package com.javamaster.spring_crud.entity;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String patherName;
    private String lastName;
    private String position;
    private Long salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "departments_employees",
            joinColumns = @JoinColumn(name = "employees_fk"),
            inverseJoinColumns = @JoinColumn(name = "departments_fk"))
    private Department department;

    public Employee() {
    }

    public Employee(String firstName, String patherName, String lastName, String position, Long salary) {
        this.firstName = firstName;
        this.patherName = patherName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatherName() {
        return patherName;
    }

    public void setPatherName(String patherName) {
        this.patherName = patherName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
