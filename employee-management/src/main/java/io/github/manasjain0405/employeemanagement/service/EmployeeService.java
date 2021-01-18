package io.github.manasjain0405.employeemanagement.service;

import io.github.manasjain0405.employeemanagement.exceptions.EmployeeNotFoundException;
import io.github.manasjain0405.employeemanagement.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeDetails(final Long id) throws EmployeeNotFoundException;

    List<Employee> getAllEmployees();

    void addEmployee(final Employee employee);

    void removeEmployee(final Long id);

    Employee modifyEmployee(final Employee employee);
}
