package com.emp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private Integer id;
    private String name;
    private String email;
    private String department;
    private BigDecimal salary;
    private LocalDate hiredAt;

    public Employee() {
    }

    public Employee(Integer id, String name, String email, String department, BigDecimal salary, LocalDate hiredAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.hiredAt = hiredAt;
    }

    // Getters/Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getHiredAt() {
        return hiredAt;
    }

    public void setHiredAt(LocalDate hiredAt) {
        this.hiredAt = hiredAt;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", dept='" + department + '\'' + ", salary=" + salary + ", hiredAt=" + hiredAt + '}';
    }
}
