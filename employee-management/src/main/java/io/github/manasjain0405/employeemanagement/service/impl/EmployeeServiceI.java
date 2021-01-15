package io.github.manasjain0405.employeemanagement.service.impl;

import io.github.manasjain0405.employeemanagement.exceptions.EmployeeNotFoundException;
import io.github.manasjain0405.employeemanagement.model.Employee;
import io.github.manasjain0405.employeemanagement.repository.EmployeeRepo;
import io.github.manasjain0405.employeemanagement.service.EmployeeService;
import io.github.manasjain0405.employeemanagement.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceI implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee getEmployeeDetails(Long id) throws EmployeeNotFoundException {
        return employeeRepo.findById(id)
                .orElseThrow(ExceptionUtils.bind(EmployeeNotFoundException::new,
                        String.format("Employee with id %d not found", id)));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepo.save(employee);

    }

    @Override
    public void removeEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public void modifyEmployee(Long id, Employee employee) {
        employee.setId(id);
        employeeRepo.save(employee);
    }
}
