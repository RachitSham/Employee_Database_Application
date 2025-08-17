package com.emp.dao;


import com.emp.model.Employee;
import com.emp.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    // CREATE
    public int insert(Employee e) {
        String sql = "INSERT INTO employees(name, email, department, salary, hired_at) " +
                "VALUES(?,?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, e.getName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getDepartment());
            ps.setBigDecimal(4, e.getSalary());
            ps.setDate(5, Date.valueOf(e.getHiredAt()));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) e.setId(rs.getInt(1));
                }
            }
            return rows;
        } catch (SQLException ex) {
            throw new RuntimeException("Insert failed: " + ex.getMessage(), ex);
        }
    }

    // READ by ID
    public Employee findById(int id) {
        String sql = "SELECT id, name, email, department, salary, hired_at FROM employees WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("FindById failed: " + ex.getMessage(), ex);
        }
    }

    // READ all
    public List<Employee> findAll() {
        String sql = "SELECT id, name, email, department, salary, hired_at FROM employees ORDER BY id";
        List<Employee> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException("FindAll failed: " + ex.getMessage(), ex);
        }
    }

    // UPDATE
    public int update(Employee e) {
        String sql = "UPDATE employees SET name=?, email=?, department=?, salary=?, hired_at=? WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getDepartment());
            ps.setBigDecimal(4, e.getSalary());
            ps.setDate(5, Date.valueOf(e.getHiredAt()));
            ps.setInt(6, e.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Update failed: " + ex.getMessage(), ex);
        }
    }

    // DELETE
    public int delete(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Delete failed: " + ex.getMessage(), ex);
        }
    }

    // Helper: map a row to Employee
    private Employee mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String dept = rs.getString("department");
        BigDecimal salary = rs.getBigDecimal("salary");
        LocalDate hiredAt = rs.getDate("hired_at").toLocalDate();
        return new Employee(id, name, email, dept, salary, hiredAt);
    }
}
