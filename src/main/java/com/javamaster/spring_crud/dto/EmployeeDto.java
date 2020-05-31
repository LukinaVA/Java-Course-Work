package com.javamaster.spring_crud.dto;

public class EmployeeDto {
    private Long id;
    private String firstName;
    private String patherName;
    private String lastName;
    private String position;
    private Long salary;
    private String department;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String firstName, String patherName, String lastName, String position,
                       Long salary, String department) {
        this.id = id;
        this.firstName = firstName;
        this.patherName = patherName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.department = department;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
