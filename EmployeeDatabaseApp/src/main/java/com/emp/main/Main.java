package com.emp.main;

import com.emp.model.Employee;
import com.emp.service.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        EmployeeService service = new EmployeeService();
        boolean running = true;

        System.out.println("== Employee DB (JDBC) ==");

        while (running) {
            System.out.println("\n1) Add  2) View by ID  3) View All  4) Update  5) Delete  6) Exit");
            System.out.print("Choice: ");
            String ch = sc.nextLine().trim();

            try {
                switch (ch) {
                    case "1" -> {
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Department: ");
                        String dept = sc.nextLine();
                        System.out.print("Salary (e.g., 55000.00): ");
                        BigDecimal sal = new BigDecimal(sc.nextLine().trim());
                        System.out.print("Hire date (YYYY-MM-DD): ");
                        LocalDate hired = LocalDate.parse(sc.nextLine().trim());
                        Employee e = service.add(name, email, dept, sal, hired);
                        System.out.println("Added: " + e);
                    }
                    case "2" -> {
                        System.out.print("ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        Employee e = service.get(id);
                        System.out.println(e == null ? "Not found" : e);
                    }
                    case "3" -> {
                        List<Employee> all = service.getAll();
                        if (all.isEmpty()) System.out.println("(no rows)");
                        else all.forEach(System.out::println);
                    }
                    case "4" -> {
                        System.out.print("ID to update: ");
                        int id = Integer.parseInt(sc.nextLine());
                        Employee existing = service.get(id);
                        if (existing == null) {
                            System.out.println("Not found");
                            break;
                        }       // [37]

                        System.out.print("Name [" + existing.getName() + "]: ");
                        String name = orDefault(sc.nextLine(), existing.getName());
                        System.out.print("Email [" + existing.getEmail() + "]: ");
                        String email = orDefault(sc.nextLine(), existing.getEmail());
                        System.out.print("Dept [" + existing.getDepartment() + "]: ");
                        String dept = orDefault(sc.nextLine(), existing.getDepartment());
                        System.out.print("Salary [" + existing.getSalary() + "]: ");
                        String salIn = sc.nextLine();
                        BigDecimal sal = salIn.isBlank() ? existing.getSalary() : new BigDecimal(salIn.trim());
                        System.out.print("Hire date [" + existing.getHiredAt() + "]: ");
                        String hdIn = sc.nextLine();
                        LocalDate hired = hdIn.isBlank() ? existing.getHiredAt() : LocalDate.parse(hdIn.trim());

                        Employee updated = new Employee(id, name, email, dept, sal, hired);
                        int rows = service.update(updated);
                        System.out.println(rows > 0 ? "Updated." : "No changes.");
                    }
                    case "5" -> {
                        System.out.print("ID to delete: ");
                        int id = Integer.parseInt(sc.nextLine());
                        int rows = service.delete(id);
                        System.out.println(rows > 0 ? "Deleted." : "Not found.");
                    }
                    case "6" -> {
                        running = false;
                        System.out.println("Bye!");
                    }
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        sc.close();
    }

    private static String orDefault(String input, String fallback) {
        return (input == null || input.isBlank()) ? fallback : input.trim();
    }
}
