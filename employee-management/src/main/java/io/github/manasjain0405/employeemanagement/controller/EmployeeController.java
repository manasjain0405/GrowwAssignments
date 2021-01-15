package io.github.manasjain0405.employeemanagement.controller;

import io.github.manasjain0405.employeemanagement.exceptions.EmployeeNotFoundException;
import io.github.manasjain0405.employeemanagement.model.Employee;
import io.github.manasjain0405.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public Employee getEmployeeInfo(@PathVariable("employeeId") Long employeeId) {

        Employee employee = null;
        try {
            employee = employeeService.getEmployeeDetails(employeeId);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e);
        }
        return employee;
    }

    @GetMapping("/listAll")
    public List<Employee> getAllEmployeeInfo() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.removeEmployee(employeeId);
    }

    @PutMapping("/{employeeId}")
    public void modifyEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody Employee employee) {
        employeeService.modifyEmployee(employeeId, employee);
    }

    @PutMapping("/")
    public void modifyEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }
}
