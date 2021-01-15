package io.github.manasjain0405.employeemanagement.service;

import io.github.manasjain0405.employeemanagement.exceptions.EmployeeNotFoundException;
import io.github.manasjain0405.employeemanagement.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeDetails(Long id) throws EmployeeNotFoundException;

    List<Employee> getAllEmployees();

    void addEmployee(Employee employee);

    void removeEmployee(Long id);

    void modifyEmployee(Long id, Employee employee);
}
