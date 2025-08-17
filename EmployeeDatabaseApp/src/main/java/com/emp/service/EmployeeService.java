package com.emp.service;

import com.emp.dao.EmployeeDao;
import com.emp.model.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EmployeeService {
    private final EmployeeDao dao = new EmployeeDao();

    public Employee add(String name, String email, String dept,
                        BigDecimal salary, LocalDate hiredAt) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email required");
        if (dept == null || dept.isBlank()) throw new IllegalArgumentException("Department required");
        if (salary == null || salary.signum() <= 0) throw new IllegalArgumentException("Salary > 0");
        if (hiredAt == null) throw new IllegalArgumentException("Hire date required");

        Employee e = new Employee(null, name.trim(), email.trim(), dept.trim(), salary, hiredAt);
        dao.insert(e);
        return e;
    }

    public Employee get(int id) {
        return dao.findById(id);
    }

    public List<Employee> getAll() {
        return dao.findAll();
    }

    public int update(Employee e) {
        return dao.update(e);
    }

    public int delete(int id) {
        return dao.delete(id);
    }
}
